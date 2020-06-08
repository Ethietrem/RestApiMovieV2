package pl.wsb.students.repository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractRepository<T, ID extends Serializable> implements IRepository<T, ID> {

    //zapisanie, usunięcie, znalezienie dabych z bazy, ogólne funkcje

    @Override
    public T merge(T entity) {
        return EntityManagerHelper.entityManager().merge(entity);
    }

    @Override
    public void delete(T entity) {
        if (entity != null) {
            EntityManagerHelper.entityManager().remove(entity);
        } //if
    }

    @Override
    public T find(ID id) {
        return EntityManagerHelper.entityManager().find(getPersistentClass(), id);
    }

    protected abstract Class<T> getPersistentClass();

    //obsługa wyników zapytan
    //pobranie pierwszego wyniku z listy lub null
    protected T getFirstResultOrNull(List<T> results){
        if (results == null){
            return null;
        }
        if (results.isEmpty()){
            return null;
        }
        return results.get(0);
    }

    protected static <T> T firstResultOrNull(List<T> results){
        if (results == null){
            return null;
        }
        if (results.isEmpty()){
            return null;
        }
        return results.get(0);
    }
}