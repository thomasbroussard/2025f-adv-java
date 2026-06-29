package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateStudentDAO extends GenericHibernateDAO<Student> implements IStudentDAO{



    public HibernateStudentDAO(SessionFactory sf){
        super(sf);
    }


    @Override
    public List<Student> search(Student qbe) {
        Session session = sf.openSession();
        Query<Student> query = session.createQuery("FROM Student", Student.class);
        return query.list();
    }

    @Override
    public Student getById(Object id) {
        return null;
    }
}
