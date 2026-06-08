package fr.epita.quiz.test;

import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.EmbeddedStudentDAO;
import fr.epita.quiz.services.IStudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class TestConfig {


    @Bean
    public DataSource dataSource(){
        return new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    }

    @Bean
    public IStudentDAO getDao(@Autowired DataSource ds){
        return new EmbeddedStudentDAO(ds);
    }

    public IStudentDAO getFakeDao(@Autowired DataSource ds){
        return new IStudentDAO() {
            @Override
            public void create(Student student) {
            }

            @Override
            public void update(Student student) {

            }

            @Override
            public void delete(Student student) {

            }

            @Override
            public List<Student> search(String id) {
                return List.of();
            }

            @Override
            public Student getById(String id) {
                return null;
            }
        };
    }
}
