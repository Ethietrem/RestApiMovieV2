package pl.wsb.students.hibernatemodel;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * MovieComment generated by hbm2java
 */
@Entity
@Table(name = "movie_comment", catalog = "java_wsb", uniqueConstraints =
@UniqueConstraint(columnNames = {"movie_id", "user_id" }))
public class MovieComment  implements java.io.Serializable {
    private Integer id;
    private Movie movie;
    private UserAccount userAccount;
    private Date created;
    private Date modified;
    private String comments;
    private int deleted;

    public MovieComment() {
    }

    public MovieComment(Movie movie, UserAccount userAccount, Date modified, int deleted) {
        this.movie = movie;
        this.userAccount = userAccount;
        this.modified = modified;
        this.deleted = deleted;
    }

    public MovieComment(Movie movie, UserAccount userAccount, Date created, Date modified, String comments, int deleted) {
        this.movie = movie;
        this.userAccount = userAccount;
        this.created = created;
        this.modified = modified;
        this.comments = comments;
        this.deleted = deleted;
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
    @JoinColumn(name = "movie_id", nullable = false)
    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserAccount getUserAccount() {
        return this.userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
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

    @Column(name = "comments")
    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "deleted")
    public int getDeleted() {
        return this.deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}