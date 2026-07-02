package fr.epita.quiz.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class GenericHibernateDAO<T> implements IDAO<T> {


    SessionFactory sf;


    public GenericHibernateDAO(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void create(T entity) {
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(entity);
            tx.commit();
        }
    }

}
