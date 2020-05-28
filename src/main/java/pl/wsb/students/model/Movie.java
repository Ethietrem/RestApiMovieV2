/*
 * Java WSB Course Sample Movie App API
 * Sample Movie App API
 *
 * OpenAPI spec version: 0.0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package pl.wsb.students.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * Movie
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-05-28T14:20:49.404Z")
public class Movie   {
  @JsonProperty("id")
  private Integer id = null;

  /**
   * Movie genre
   */
  public enum GenreEnum {
    ACTION("Action"),

    COMEDY("Comedy"),

    DRAMA("Drama"),

    SCIENCE_FICTION("Science fiction"),

    THRILLER("Thriller");

    private String value;

    GenreEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static GenreEnum fromValue(String text) {
      for (GenreEnum b : GenreEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("genre")
  private GenreEnum genre = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("rating")
  private Integer rating = null;

  @JsonProperty("year")
  private String year = null;

  @JsonProperty("director")
  private String director = null;

  @JsonProperty("actors")
  private List<Actor> actors = null;

  @JsonProperty("comment")
  private String comment = null;

  public Movie id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Movie id
   * @return id
   **/
  @JsonProperty("id")
  @ApiModelProperty(required = true, value = "Movie id")
  @NotNull
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Movie genre(GenreEnum genre) {
    this.genre = genre;
    return this;
  }

  /**
   * Movie genre
   * @return genre
   **/
  @JsonProperty("genre")
  @ApiModelProperty(value = "Movie genre")
  public GenreEnum getGenre() {
    return genre;
  }

  public void setGenre(GenreEnum genre) {
    this.genre = genre;
  }

  public Movie title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Movie title
   * @return title
   **/
  @JsonProperty("title")
  @ApiModelProperty(required = true, value = "Movie title")
  @NotNull
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Movie rating(Integer rating) {
    this.rating = rating;
    return this;
  }

  /**
   * Movie rating
   * @return rating
   **/
  @JsonProperty("rating")
  @ApiModelProperty(value = "Movie rating")
  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public Movie year(String year) {
    this.year = year;
    return this;
  }

  /**
   * Movie release year
   * @return year
   **/
  @JsonProperty("year")
  @ApiModelProperty(value = "Movie release year")
  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public Movie director(String director) {
    this.director = director;
    return this;
  }

  /**
   * Movie director
   * @return director
   **/
  @JsonProperty("director")
  @ApiModelProperty(value = "Movie director")
  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public Movie actors(List<Actor> actors) {
    this.actors = actors;
    return this;
  }

  public Movie addActorsItem(Actor actorsItem) {
    if (this.actors == null) {
      this.actors = new ArrayList<Actor>();
    }
    this.actors.add(actorsItem);
    return this;
  }

  /**
   * Get actors
   * @return actors
   **/
  @JsonProperty("actors")
  @ApiModelProperty(value = "")
  public List<Actor> getActors() {
    return actors;
  }

  public void setActors(List<Actor> actors) {
    this.actors = actors;
  }

  public Movie comment(String comment) {
    this.comment = comment;
    return this;
  }

  /**
   * Movie coomment
   * @return comment
   **/
  @JsonProperty("comment")
  @ApiModelProperty(value = "Movie coomment")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Movie movie = (Movie) o;
    return Objects.equals(this.id, movie.id) &&
            Objects.equals(this.genre, movie.genre) &&
            Objects.equals(this.title, movie.title) &&
            Objects.equals(this.rating, movie.rating) &&
            Objects.equals(this.year, movie.year) &&
            Objects.equals(this.director, movie.director) &&
            Objects.equals(this.actors, movie.actors) &&
            Objects.equals(this.comment, movie.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, genre, title, rating, year, director, actors, comment);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Movie {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    genre: ").append(toIndentedString(genre)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    director: ").append(toIndentedString(director)).append("\n");
    sb.append("    actors: ").append(toIndentedString(actors)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}