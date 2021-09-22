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
import java.util.Objects;

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

        var authorizationHeader = request.getHeader(AUTHORIZATION);

        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            var token = authorizationHeader.substring("Bearer ".length());

            var authenticationToken = getAuthenticationToken(token);

            if (Objects.nonNull(authenticationToken)) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        if (jwtUtil.tokenIsValid(token)) {
            var username = jwtUtil.getUsername(token);
            var authorities = jwtUtil.getAuthorities(token);

            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }

        return null;
    }
}
