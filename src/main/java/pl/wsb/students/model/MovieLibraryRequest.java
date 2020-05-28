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
import javax.validation.constraints.*;

/**
 * MovieLibraryRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-05-28T14:20:49.404Z")
public class MovieLibraryRequest   {
  @JsonProperty("movie_id")
  private Integer movieId = null;

  /**
   * My status for movie
   */
  public enum StatusEnum {
    SEEN("Seen"),

    WANT_TO_SEE("Want to see");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  public MovieLibraryRequest movieId(Integer movieId) {
    this.movieId = movieId;
    return this;
  }

  /**
   * Movie to rate id
   * @return movieId
   **/
  @JsonProperty("movie_id")
  @ApiModelProperty(required = true, value = "Movie to rate id")
  @NotNull
  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public MovieLibraryRequest status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * My status for movie
   * @return status
   **/
  @JsonProperty("status")
  @ApiModelProperty(required = true, value = "My status for movie")
  @NotNull
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MovieLibraryRequest movieLibraryRequest = (MovieLibraryRequest) o;
    return Objects.equals(this.movieId, movieLibraryRequest.movieId) &&
            Objects.equals(this.status, movieLibraryRequest.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(movieId, status);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MovieLibraryRequest {\n");

    sb.append("    movieId: ").append(toIndentedString(movieId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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