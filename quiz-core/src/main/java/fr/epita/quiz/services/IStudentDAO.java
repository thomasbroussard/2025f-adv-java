package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Student;

import java.util.List;

public interface IStudentDAO {
    void create(Student student);

    void update(Student student);

    void delete(Student student);

    List<Student> search(String id);

    Student getById(String id);
}
