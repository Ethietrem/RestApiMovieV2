package pl.wsb.students.hibernatemodel;
// Generated 2020-06-02 20:10:05 by Hibernate Tools 4.3.1


import pl.wsb.students.exceptions.ApiException;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Movie generated by hbm2java
 */
@Entity
@Table(name = "movie", catalog = "java_wsb")
public class Movie  implements java.io.Serializable {
     private Integer id;
     private Director director;
     private MovieGenre movieGenre;
     private Date created;
     private Date modified;
     private String title;
     private String releaseYear;
    private MovieRequestStatus movieRequestStatus;
    private Integer deleted;
    private Set<MovieLibrary> movieLibraries = new HashSet<>(0);
    private Set<MovieActor> movieActors = new HashSet<>(0);
    private Set<MovieRating> movieRatings = new HashSet<>(0);
    private Set<MovieComment> movieComments = new HashSet<>(0);

    public Movie() {
    }

    public Movie(Date modified) {
        this.modified = modified;
    }

    public Movie(Director director, MovieGenre movieGenre, Date created, Date modified, String title, String releaseYear, MovieRequestStatus
            movieRequestStatus, Integer deleted, Set<MovieLibrary> movieLibraries, Set<MovieActor> movieActors, Set<MovieRating> movieRatings) {
       this.director = director;
       this.movieGenre = movieGenre;
       this.created = created;
       this.modified = modified;
       this.title = title;
       this.releaseYear = releaseYear;
       this.movieRequestStatus = movieRequestStatus;
       this.deleted = deleted;
       this.movieLibraries = movieLibraries;
       this.movieActors = movieActors;
       this.movieRatings = movieRatings;
       this.movieComments = movieComments;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    public Director getDirector() {
        return this.director;
    }
    
    public void setDirector(Director director) {
        this.director = director;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    public MovieGenre getMovieGenre() {
        return this.movieGenre;
    }
    
    public void setMovieGenre(MovieGenre movieGenre) {
        this.movieGenre = movieGenre;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", length = 19)
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified", nullable = false, length = 19)
    public Date getModified() {
        return this.modified;
    }
    
    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Column(name = "title")
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "release_year", length = 80)
    public String getReleaseYear() {
        return this.releaseYear;
    }
    
    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_status_id")
    public MovieRequestStatus getMovieRequestStatus() {
        return this.movieRequestStatus;
    }
    public void setMovieRequestStatus(MovieRequestStatus movieRequestStatus) {
        this.movieRequestStatus = movieRequestStatus;
    }

    @Column(name = "deleted")
    public Integer getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    public Set<MovieLibrary> getMovieLibraries() {
        return this.movieLibraries;
    }
    
    public void setMovieLibraries(Set<MovieLibrary> movieLibraries) {
        this.movieLibraries = movieLibraries;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    public Set<MovieActor> getMovieActors() {
        return this.movieActors;
    }
    
    public void setMovieActors(Set<MovieActor> movieActors) {
        this.movieActors = movieActors;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    public Set<MovieRating> getMovieRatings() {
        return this.movieRatings;
    }
    
    public void setMovieRatings(Set<MovieRating> movieRatings) {
        this.movieRatings = movieRatings;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    public Set<MovieComment> getMovieComments() {return this.movieComments;}

    public void setMovieComments(Set<MovieComment> movieComments) {this.movieComments = movieComments;}
}