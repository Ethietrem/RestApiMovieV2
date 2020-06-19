package pl.wsb.students.api.handlers;

import pl.wsb.students.hibernatemodel.UserAccount;
import pl.wsb.students.repository.impl.UserAccountRepository;
import pl.wsb.students.security.MovieAppSecurityContext;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class SecurityContextHandler {
    /**
     * Returns user from security context.
     */
    public static UserAccount getUserAccount(SecurityContext context) {
        try {
            if (context == null) {
                return null;
            }    //if
            if (context instanceof MovieAppSecurityContext) {
                return ((MovieAppSecurityContext) context).getUser();
            }    //if
            //weryfikacja kontekstu
            Principal principal = context.getUserPrincipal();
            if (principal == null) {
                return null;
            }    //if
            UserAccountRepository userAccountRepository = new UserAccountRepository();
            return userAccountRepository.findByEmail(principal.getName());
        } catch (Throwable ex) {
            return null;
        }
    }
}
