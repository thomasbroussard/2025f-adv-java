package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class GenericHibernateDAO<T> implements IDAO<T> {


    SessionFactory sf;


    public GenericHibernateDAO(SessionFactory sf){
        this.sf = sf;
    }

    @Override
    public void create(T student) {
        Session session = sf.openSession();
        session.persist(student);
        session.flush();
    }

    @Override
    public void update(T student) {
        Session session = sf.openSession();
        session.merge(student);
        session.flush();
    }

    @Override
    public void delete(T student) {
        Session session = sf.openSession();
        session.remove(student);
        session.flush();
    }

}
