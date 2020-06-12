package pl.wsb.students.repository.impl;

import pl.wsb.students.hibernatemodel.Movie;
import pl.wsb.students.repository.AbstractRepository;

public class MovieCommentRepository extends AbstractRepository<Movie, Integer>{
    @Override
    protected Class<Movie> getPersistentClass() {
        return Movie.class;
    }
}