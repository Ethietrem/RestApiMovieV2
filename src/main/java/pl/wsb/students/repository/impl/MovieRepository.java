package pl.wsb.students.repository.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernatemodel.Director;
import pl.wsb.students.hibernatemodel.Movie;
import pl.wsb.students.hibernatemodel.MovieGenre;
import pl.wsb.students.model.MovieRequest;
import pl.wsb.students.model.RegisterUserRequest;
import pl.wsb.students.repository.AbstractRepository;
import pl.wsb.students.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;

public class MovieRepository extends AbstractRepository<Movie, Integer> {
    @Override
    protected Class<Movie> getPersistentClass() {
        return Movie.class;
    }

    public Movie findByName(String title) {
        if (StringUtils.isBlank(title)) {
            return null;
        } //if
        CriteriaBuilder criteriaBuilder =
                EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery =
                criteriaBuilder.createQuery(Movie.class);
        Root<Movie> movie = criteriaQuery.from(Movie.class);
        criteriaQuery.where(
                criteriaBuilder.equal(
                        criteriaBuilder.lower(
                                movie.<String>get("title")
                        ),
                        title.toLowerCase())
        );
        return getFirstResultOrNull(
                EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList()
        );
    }

    //tworzenie nowego movie
    public Movie addMovie(MovieRequest movieRequest) throws ValidationException {
        if (movieRequest == null) {
            throw new ValidationException("movieRequest");
        } //if
        movieRequest.validateData();
        Movie newMovie = findByName(movieRequest.getTitle());
        if (newMovie == null) {
            throw new ValidationException("Provided title not found...");
        } //if
        newMovie = new Movie();
        newMovie.setCreated(new Date());
        newMovie.setModified(new Date());
        newMovie.setTitle(movieRequest.getTitle());
        //newMovie.setDirector(new Director());
        //newMovie.setMovieGenre(new MovieGenre());
        EntityManagerHelper.startTransaction();
        newMovie = merge(newMovie);
        EntityManagerHelper.commitTransaction();
        MovieRepository movieRepository = new MovieRepository();
        //movieRepository.assignUserToRole(newMovie, RoleRepository.findByAbbr("MOVIE"));
        return newMovie;
    }
}