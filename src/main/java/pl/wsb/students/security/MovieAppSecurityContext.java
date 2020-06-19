package pl.wsb.students.security;

import pl.wsb.students.hibernatemodel.UserAccount;

import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;

public class MovieAppSecurityContext implements SecurityContext {

    private final UserAccount user;
    private final UriInfo uriInfo;

    public MovieAppSecurityContext(UserAccount user, UriInfo uriInfo) {
        this.user = user;
        this.uriInfo = uriInfo;
    }

    @Override
    public Principal getUserPrincipal() {
        return new MovieAppPrincipal(this.user);
    }

    //sprawdzenie czy pobrany user ma nadaną jakąś rolę
    @Override
    public boolean isUserInRole(String s) {
        return true;
    }

    //sprawdzenie czy połączenie nasepuje za pomocą https czy też nie
    @Override
    public boolean isSecure() {
        if (this.uriInfo == null) {
            return false;
        } //if
        return this.uriInfo.getAbsolutePath().toString().startsWith("https");
    }

    //wprowadzona własna nazwa uwierzytelnienia
    @Override
    public String getAuthenticationScheme() {
        return "MovieResponse-App-Auth-Scheme";
    }

    public UserAccount getUser() {
        return user;
    }
}
