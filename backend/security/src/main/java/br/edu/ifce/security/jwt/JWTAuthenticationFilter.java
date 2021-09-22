package br.edu.ifce.security.jwt;

import br.edu.ifce.security.user.UserSecurityService;
import br.edu.ifce.usecase.ports.requests.UserCredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JWTUtil jwtUtil
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        var userCredentials = getUsernameAndPasswordFromRequest(request);

        var authenticationToken = new UsernamePasswordAuthenticationToken(
                userCredentials.getEmail(),
                userCredentials.getPassword()
        );

        return authenticationManager.authenticate(authenticationToken);
    }

    private UserCredentialsDTO getUsernameAndPasswordFromRequest(HttpServletRequest request) {
        try {
            return new ObjectMapper().readValue(request.getInputStream(), UserCredentialsDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException {

        var userDetails = (UserSecurityService) authentication.getPrincipal();

        var access_token = jwtUtil.getAccessToken(userDetails);
        var refresh_token = jwtUtil.getRefreshToken(userDetails);

        sendAccessTokenAndRefreshTokenInResponseBody(access_token, refresh_token, response);
    }

    private void sendAccessTokenAndRefreshTokenInResponseBody(String accessToken,
                                                              String refreshToken,
                                                              HttpServletResponse response) throws IOException {

        Map<String, String> tokens = new HashMap<>();

        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        var jsonMessage = new Gson().toJson(tokens);

        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().append(jsonMessage);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {

        response.setStatus(UNAUTHORIZED.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().append(getErrorMessage(request.getRequestURI()));
    }

    private String getErrorMessage(String path) {
        var message = new ErrorMessage(
                Instant.now().toString(),
                UNAUTHORIZED.value(),
                UNAUTHORIZED.getReasonPhrase(),
                "Invalid credentials",
                path
        );

        return new Gson().toJson(message);
    }

    @AllArgsConstructor
    private static final class ErrorMessage {
        private final String timestamp;
        private final Integer status;
        private final String error;
        private final String message;
        private final String path;
    }
}
