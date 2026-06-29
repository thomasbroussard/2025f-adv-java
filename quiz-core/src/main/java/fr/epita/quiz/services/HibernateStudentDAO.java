package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Properties;

public class HibernateStudentDAO implements IStudentDAO {

    SessionFactory sf;


    public HibernateStudentDAO(SessionFactory sf){
        this.sf = sf;
    }

    @Override
    public void create(Student student) {
        Session session = sf.openSession();
        session.persist(student);
        session.flush();
    }

    @Override
    public void update(Student student) {
        Session session = sf.openSession();
        session.merge(student);
        session.flush();
    }

    @Override
    public void delete(Student student) {
        Session session = sf.openSession();
        session.remove(student);
        session.flush();
    }

    @Override
    public List<Student> search(String id) {
        Session session = sf.openSession();
        Query<Student> query = session.createQuery("FROM Student", Student.class);
        return query.list();
    }

    @Override
    public Student getById(String id) {
        return null;
    }
}
