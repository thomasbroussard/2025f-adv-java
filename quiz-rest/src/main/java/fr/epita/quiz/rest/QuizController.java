package fr.epita.quiz.rest;


import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.rest.model.StudentDTO;
import fr.epita.quiz.services.IStudentDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {

    @Autowired
    private IStudentDAO studentDAO;

    @GetMapping
    public StudentDTO getStudent(int id){
        Student student = studentDAO.getById(id);

        // mapping to external model to be done
        //student to StudentDto mapping to be done
        return student;
    }
}
