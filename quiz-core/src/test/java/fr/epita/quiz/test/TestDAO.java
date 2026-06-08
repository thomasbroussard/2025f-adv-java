package fr.epita.quiz.test;

import fr.epita.quiz.services.EmbeddedStudentDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestDAO {

    @Autowired
    EmbeddedStudentDAO dao;



    @Test
    public void test(){
        Assertions.assertNotNull(dao);

    }
}
