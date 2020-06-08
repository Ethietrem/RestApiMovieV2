package pl.wsb.students.repository.impl;

import pl.wsb.students.hibernatemodel.Director;
import pl.wsb.students.repository.AbstractRepository;

public class DirectorRepository extends AbstractRepository<Director, Integer> {
    @Override
    protected Class<Director> getPersistentClass() {
        return Director.class;
    }
}