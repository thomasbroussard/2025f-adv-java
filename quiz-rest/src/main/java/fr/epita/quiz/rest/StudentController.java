package fr.epita.quiz.rest;

import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.rest.model.StudentDTO;
import fr.epita.quiz.services.IStudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private IStudentDAO studentDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDTO> getStudents() {
        return studentDAO.search(new Student())
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") String id) {
        Student student = studentDAO.getById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDTO(student));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO dto) {
        Student student = toEntity(dto);
        studentDAO.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(student));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") String id, @RequestBody StudentDTO dto) {
        dto.setId(id);
        Student student = toEntity(dto);
        studentDAO.update(student);
        return ResponseEntity.ok(toDTO(student));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") String id) {
        Student student = studentDAO.getById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        studentDAO.delete(student);
        return ResponseEntity.noContent().build();
    }

    private StudentDTO toDTO(Student student) {
        return new StudentDTO(student.getId(), student.getName(), student.getAddress());
    }

    private Student toEntity(StudentDTO dto) {
        return new Student(dto.getId(), dto.getName(), dto.getAddress());
    }
}
