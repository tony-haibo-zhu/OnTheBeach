package hello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class CustomAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter{
    @Autowired
    public CustomAuthenticationFilter(@Qualifier("authenticationManager") AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
//        UserPrivilege userPrivilege = new UserPrivilege();
//        userPrivilege.setUserName("abc");
//
//        return userPrivilege;
        String token = request.getHeader(AUTHORIZATION);

        if (token != null) {
            UserPrivilege userPrivilege = new UserPrivilege();
            userPrivilege.setUserName("test");
            List<String> list = new ArrayList<>();
            list.add("ABC");
            userPrivilege.setPrivilegeCodes(list);
            return userPrivilege;
        } else {
            return null;
        }
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }
}
