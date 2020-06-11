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
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import pl.wsb.students.exceptions.ValidationException;

import javax.validation.constraints.*;

/**
 * UpdateUserRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-05-28T14:20:49.404Z")
public class UpdateUserRequest   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("password")
  private String password = null;

  public UpdateUserRequest id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * User id
   * @return id
   **/
  @JsonProperty("id")
  @ApiModelProperty(required = true, value = "User id")
  @NotNull
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public UpdateUserRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * User email address
   * @return email
   **/
  @JsonProperty("email")
  @ApiModelProperty(required = true, value = "User email address")
  @NotNull
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UpdateUserRequest password(String password) {
    this.password = password;
    return this;
  }

  /**
   * User password
   * @return password
   **/
  @JsonProperty("password")
  @ApiModelProperty(required = true, value = "User password")
  @NotNull
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateUserRequest updateUserRequest = (UpdateUserRequest) o;
    return Objects.equals(this.id, updateUserRequest.id) &&
            Objects.equals(this.email, updateUserRequest.email) &&
            Objects.equals(this.password, updateUserRequest.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateUserRequest {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

  /*
  Metoda pomocnicza do walidacji danych rejestracyjnych Użytkownika

  Metoda ta sprawdza niepustość dostarczonych danych,
  oraz rzuca odpowiednim wyjątkiem w razie potrzeby
   */
  public void validateData() throws ValidationException {
    if (StringUtils.isBlank(this.email)) {
      throw new ValidationException("Please provide email address...");
    }
  }
}