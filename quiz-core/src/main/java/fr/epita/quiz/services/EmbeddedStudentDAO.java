package fr.epita.quiz.services;


import fr.epita.quiz.datamodel.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class EmbeddedStudentDAO {


    private final DataSource ds;

    public EmbeddedStudentDAO(DataSource datasource){
        this.ds = datasource;
        try(Connection cnx= datasource.getConnection()){

        }catch (Exception e){

        }
    }


    public void create(Student student){

    }

    public void update(Student student){

    }

    public void delete(Student student){

    }

    public List<Student> search(String id){
        return new ArrayList<>();
    }

    public Student getById(String id){
        return null;
    }


}
