package br.edu.ifce.security;

import br.edu.ifce.security.jwt.JWTUtil;
import br.edu.ifce.security.user.UserSecurityService;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public AuthenticatedUser getAuthenticatedUser() {
        try {
            var authentication = SecurityContextHolder.getContext().getAuthentication();

            var email = authentication.getPrincipal().toString();
            var isAuthenticated = authentication.isAuthenticated();
            var roles = authentication.getAuthorities().stream()
                    .map(Objects::toString)
                    .collect(Collectors.toSet());

            return new AuthenticatedUser(email, isAuthenticated, roles);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String refreshAuthToken(String email) {
        var userDetails = userDetailsService.loadUserByUsername(email);

        return jwtUtil.getAccessToken((UserSecurityService) userDetails);
    }
}
