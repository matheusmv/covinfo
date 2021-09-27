package br.edu.ifce.security;

import br.edu.ifce.security.jwt.JWTUtil;
import br.edu.ifce.security.user.UserSecurityService;
import br.edu.ifce.usecase.exceptions.AuthorizationException;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;

    @Override
    public AuthenticatedUser getAuthenticatedUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(toAuthenticatedUser)
                .orElseThrow(() -> new AuthorizationException("unauthenticated"));
    }

    private final Function<Authentication, AuthenticatedUser> toAuthenticatedUser = authenticatedUser -> {
        var email = authenticatedUser.getPrincipal().toString();
        var isAuthenticated = authenticatedUser.isAuthenticated();
        var roles = authenticatedUser.getAuthorities().stream()
                .map(Objects::toString)
                .collect(Collectors.toSet());

        return new AuthenticatedUser(email, isAuthenticated, roles);
    };

    @Override
    public String refreshAuthToken(String email) {
        var userDetails = userDetailsService.loadUserByUsername(email);

        return jwtUtil.getAccessToken((UserSecurityService) userDetails);
    }
}
