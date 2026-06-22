package fr.epita.quiz.test;


import fr.epita.quiz.datamodel.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestHibernateIntegration {

    @Autowired
    private SessionFactory sf;

    @Test
    public void testCreateStudent(){
        Session session = sf.openSession();

        Student student = new Student();
        student.setAddress("Paris");
        student.setName("Test");


        session.persist(student);

        session.flush();

        session.close();


    }
}
