package pl.wsb.students.repository.impl;

import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernatemodel.MovieComment;
import pl.wsb.students.model.MovieCommentRequest;
import pl.wsb.students.repository.AbstractRepository;
import pl.wsb.students.repository.EntityManagerHelper;

import java.util.Date;

public class MovieCommentRepository extends AbstractRepository<MovieComment, Integer>{
    @Override
    protected Class<MovieComment> getPersistentClass() {
        return MovieComment.class;
    }

    //dodanie komentarza do filmu
    public MovieComment addMovieComment(MovieCommentRequest movieComment) throws ValidationException {
        if (movieComment == null) {
            throw new ValidationException("movieComment");
        } //if
        MovieComment newComment = new MovieComment();
        newComment.setCreated(new Date());
        newComment.setModified(new Date());
        newComment.setComments(newComment.getComments());
        EntityManagerHelper.startTransaction();
        newComment = merge(newComment);
        EntityManagerHelper.commitTransaction();
        return newComment;
    }
}