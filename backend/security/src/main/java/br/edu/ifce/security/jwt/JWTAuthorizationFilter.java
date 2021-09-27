package br.edu.ifce.security.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTUtil jwtUtil;

    public JWTAuthorizationFilter(
            AuthenticationManager authenticationManager,
            JWTUtil jwtUtil
    ) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        Optional.ofNullable(request.getHeader(AUTHORIZATION))
                .filter(header -> header.startsWith("Bearer "))
                .map(header -> header.substring("Bearer ".length()))
                .map(this::getAuthenticationToken)
                .ifPresent(usernamePasswordAuthenticationToken -> {
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                });

        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        return Optional.ofNullable(token)
                .filter(jwtUtil::tokenIsValid)
                .map(this::getAuthenticationDetails)
                .orElse(null);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationDetails(String jwt) {
        var username = jwtUtil.getUsername(jwt);
        var authorities = jwtUtil.getAuthorities(jwt);

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
