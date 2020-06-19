/*
 * Java WSB Course Sample MovieResponse App API
 * Sample MovieResponse App API
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
import pl.wsb.students.exceptions.ApiException;
import pl.wsb.students.hibernatemodel.UserAccount;

import javax.validation.constraints.*;

/**
 * User
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-05-28T14:20:49.404Z")
public class User   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("email")
  private String email = null;

  public User id(Integer id) {
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

  public User email(String email) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
            Objects.equals(this.email, user.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

  public static User createFromUserAccount(UserAccount userAccount) throws ApiException{
    if (userAccount == null){
      throw new ApiException("UserAccount is null ...");
    }
    return new User().email(userAccount.getEmail()).id(userAccount.getId());
  }

  public static User editUserAccount(UserAccount userAccount) throws ApiException{
    if (userAccount == null){
      throw new ApiException("UserAccount is null ...");
    }
    return new User().id(userAccount.getId());
  }

  public static User logoutFromUserAccount(UserAccount userAccount) throws ApiException{
    if (userAccount == null){
      throw new ApiException("UserAccount is null ...");
    }
    return new User().id(userAccount.getId());
  }
}