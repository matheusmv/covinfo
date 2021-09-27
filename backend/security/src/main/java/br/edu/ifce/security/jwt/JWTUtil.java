package br.edu.ifce.security.jwt;

import br.edu.ifce.security.user.UserSecurityService;
import br.edu.ifce.usecase.exceptions.ValidationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    private final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private Algorithm getSignatureAlgorithm() {
        return Algorithm.HMAC256(secret.getBytes());
    }

    public String getAccessToken(UserSecurityService userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withClaim("roles", userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(getSignatureAlgorithm());
    }

    public String getRefreshToken(UserSecurityService userDetails) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration * 10))
                .withSubject(userDetails.getUsername())
                .sign(getSignatureAlgorithm());
    }

    private JWTVerifier getJWTVerifier() {
        return JWT.require(getSignatureAlgorithm()).build();
    }

    private DecodedJWT getDecodedJWT(String authenticationToken) {
        try {
            return getJWTVerifier().verify(authenticationToken);
        } catch (JWTDecodeException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public boolean tokenIsValid(String authenticationToken) {
        return Optional.ofNullable(getDecodedJWT(authenticationToken))
                .filter(usernameIsPresent)
                .filter(rolesIsPresent)
                .filter(isNotExpired)
                .isPresent();
    }

    private final Predicate<DecodedJWT> usernameIsPresent = decodedJWT -> Optional.ofNullable(decodedJWT)
            .map(Payload::getSubject)
            .isPresent();

    private final Predicate<DecodedJWT> rolesIsPresent = decodedJWT -> Optional.ofNullable(decodedJWT)
            .map(jwt -> jwt.getClaim("roles"))
            .isPresent();

    private final Predicate<DecodedJWT> isNotExpired = decodedJWT -> Optional.ofNullable(decodedJWT)
            .map(Payload::getExpiresAt)
            .filter(expirationDate -> new Date(System.currentTimeMillis()).before(expirationDate))
            .isPresent();

    public String getUsername(String authenticationToken) {
        return Optional.ofNullable(getDecodedJWT(authenticationToken))
                .map(Payload::getSubject)
                .orElseThrow(() -> new ValidationException("it was not possible to obtain username"));
    }

    public Set<SimpleGrantedAuthority> getAuthorities(String authenticationToken) {
        return Optional.ofNullable(getDecodedJWT(authenticationToken))
                .map(decodedJWT -> decodedJWT.getClaim("roles")
                        .asList(String.class))
                .map(listOfRoles -> listOfRoles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet()))
                .orElseThrow(() -> new ValidationException("it was not possible to obtain authorities"));
    }
}
