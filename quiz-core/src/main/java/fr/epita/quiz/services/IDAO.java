package fr.epita.quiz.services;

import java.util.List;

public interface IDAO<T> {



     void create(T entity);


     void update(T entity);


     void delete(T entity) ;

     List<T> search(T qbe);

     T getById(Object id);



}
