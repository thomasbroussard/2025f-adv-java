package fr.epita.quiz.rest;


import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.rest.model.StudentDTO;
import fr.epita.quiz.services.IStudentDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class QuizController {

    //@Autowired
    //private IStudentDAO studentDAO;

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getStudent(@PathVariable("id") int id){
      //  Student student = studentDAO.getById(id);

        // mapping to external model to be done
        //student to StudentDto mapping to be done
        return new StudentDTO();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<StudentDTO> getStudents(){
        //  Student student = studentDAO.getById(id);

        // mapping to external model to be done
        //student to StudentDto mapping to be done
        StudentDTO dto = new StudentDTO();
        dto.setId("abcd");
        return ResponseEntity.of(Optional.of(dto));
    }
}
