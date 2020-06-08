package pl.wsb.students.repository;

/*
Interfejs generyczny czyli parametrem jesst też klasa (T) - encja, PK u nas int -> to klucze
tych metod jest więcej np update albo isert
 */

import java.io.Serializable;

public interface IRepository<T, PK extends Serializable> {
    T merge(T entity);
    void delete(T entity);
    T find(PK id);
}