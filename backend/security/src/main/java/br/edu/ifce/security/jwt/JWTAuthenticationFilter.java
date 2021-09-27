package br.edu.ifce.security.jwt;

import br.edu.ifce.security.user.UserSecurityService;
import br.edu.ifce.usecase.ports.requests.UserCredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JWTUtil jwtUtil
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    private UserCredentialsDTO getUsernameAndPasswordFromRequest(HttpServletRequest request) {
        try {
            return new ObjectMapper().readValue(request.getInputStream(), UserCredentialsDTO.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    private final Function<UserCredentialsDTO, UsernamePasswordAuthenticationToken> getAuthenticationToken = credentials ->
            new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        return Optional.ofNullable(getUsernameAndPasswordFromRequest(request))
                .map(getAuthenticationToken)
                .map(authenticationManager::authenticate)
                .orElse(null);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) {

        Optional.ofNullable((UserSecurityService) authentication.getPrincipal())
                .ifPresent(userSecurityService -> {
                    var access_token = jwtUtil.getAccessToken(userSecurityService);
                    var refresh_token = jwtUtil.getRefreshToken(userSecurityService);

                    sendAccessTokenAndRefreshTokenInResponseBody(access_token, refresh_token, response);
                });
    }

    private void sendAccessTokenAndRefreshTokenInResponseBody(String accessToken,
                                                              String refreshToken,
                                                              HttpServletResponse response) {
        try {
            var tokens = Map.of(
                    "access_token", accessToken,
                    "refresh_token", refreshToken);

            var jsonMessage = new Gson().toJson(tokens);

            response.setContentType(APPLICATION_JSON_VALUE);
            response.getWriter().append(jsonMessage);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {

        response.setStatus(UNAUTHORIZED.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().append(getErrorMessage(failed.getMessage(), request.getRequestURI()));
    }

    private String getErrorMessage(String message, String path) {
        var errorMessage = ErrorMessage.of(
                Instant.now().toString(),
                UNAUTHORIZED.value(),
                UNAUTHORIZED.getReasonPhrase(),
                message,
                path
        );

        return new Gson().toJson(errorMessage);
    }

    @RequiredArgsConstructor
    private static final class ErrorMessage {
        private final String timestamp;
        private final Integer status;
        private final String error;
        private final String message;
        private final String path;

        public static ErrorMessage of(String timestamp, Integer status, String error, String message, String path) {
            return new ErrorMessage(timestamp, status, error, message, path);
        }
    }
}
