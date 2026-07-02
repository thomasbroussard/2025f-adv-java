package fr.epita.quiz.rest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * End-to-end test for {@link StudentController}.
 * <p>
 * This boots the real Spring Boot application (embedded Tomcat) on a random port and
 * drives it with genuine HTTP requests issued by the JDK {@link HttpClient} — nothing is
 * mocked, requests travel over the loopback network and hit the Hibernate-backed DAO.
 */
class StudentControllerE2ETest {

    private static ConfigurableApplicationContext context;
    private static HttpClient httpClient;
    private static String baseUrl;

    @BeforeAll
    static void startApplication() {
        // --server.port=0 -> let the OS pick a free port so tests never clash with a running app.
        // Passed as a command-line arg (high precedence) so it overrides application.properties.
        context = new SpringApplicationBuilder(Launcher.class)
                .run("--server.port=0");
        int port = ((WebServerApplicationContext) context).getWebServer().getPort();
        baseUrl = "http://localhost:" + port + "/api/v1/students";
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    @AfterAll
    static void stopApplication() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    void studentLifecycleOverRealHttp() throws Exception {
        // 1. GET collection -> empty at first
        HttpResponse<String> initial = send("GET", "", null);
        assertEquals(200, initial.statusCode());

        // 2. POST -> create two students
        HttpResponse<String> createAlice =
                send("POST", "", "{\"id\":\"s1\",\"name\":\"Alice\",\"address\":\"Paris\"}");
        assertEquals(201, createAlice.statusCode());
        assertTrue(createAlice.body().contains("\"id\":\"s1\""));
        assertTrue(createAlice.body().contains("Alice"));

        HttpResponse<String> createBob =
                send("POST", "", "{\"id\":\"s2\",\"name\":\"Bob\",\"address\":\"Lyon\"}");
        assertEquals(201, createBob.statusCode());

        // 3. GET collection -> now contains both
        HttpResponse<String> list = send("GET", "", null);
        assertEquals(200, list.statusCode());
        assertTrue(list.body().contains("Alice"));
        assertTrue(list.body().contains("Bob"));

        // 4. GET /{id} -> fetch a single student
        HttpResponse<String> getAlice = send("GET", "/s1", null);
        assertEquals(200, getAlice.statusCode());
        assertTrue(getAlice.body().contains("\"address\":\"Paris\""));

        // 5. PUT /{id} -> update
        HttpResponse<String> updateAlice =
                send("PUT", "/s1", "{\"name\":\"Alice Cooper\",\"address\":\"Marseille\"}");
        assertEquals(200, updateAlice.statusCode());
        assertTrue(updateAlice.body().contains("Alice Cooper"));
        assertTrue(updateAlice.body().contains("Marseille"));

        // 6. GET /{id} -> update was persisted through Hibernate
        HttpResponse<String> getUpdated = send("GET", "/s1", null);
        assertEquals(200, getUpdated.statusCode());
        assertTrue(getUpdated.body().contains("Alice Cooper"));
        assertTrue(getUpdated.body().contains("Marseille"));

        // 7. DELETE /{id} -> remove Bob
        HttpResponse<String> deleteBob = send("DELETE", "/s2", null);
        assertEquals(204, deleteBob.statusCode());

        // 8. GET /{id} on the deleted student -> 404
        HttpResponse<String> getBob = send("GET", "/s2", null);
        assertEquals(404, getBob.statusCode());

        // 9. GET /{id} on an unknown student -> 404
        HttpResponse<String> getMissing = send("GET", "/does-not-exist", null);
        assertEquals(404, getMissing.statusCode());
    }

    private static HttpResponse<String> send(String method, String path, String jsonBody) throws Exception {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .header("Accept", "application/json");
        HttpRequest.BodyPublisher body;
        if (jsonBody == null) {
            body = HttpRequest.BodyPublishers.noBody();
        } else {
            body = HttpRequest.BodyPublishers.ofString(jsonBody);
            builder.header("Content-Type", "application/json");
        }
        HttpRequest request = builder.method(method, body).build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
