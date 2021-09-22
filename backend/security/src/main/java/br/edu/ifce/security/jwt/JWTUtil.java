package br.edu.ifce.security.jwt;

import br.edu.ifce.security.user.UserSecurityService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JWTUtil {

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

    // TODO: handle JWTDecodeException exception
    public boolean tokenIsValid(String authenticationToken) {
        if (Objects.nonNull(authenticationToken)) {
            var decodedJWT = getDecodedJWT(authenticationToken);

            var username = decodedJWT.getSubject();
            var roles = decodedJWT.getClaim("roles");
            var expirationDate = decodedJWT.getExpiresAt();
            var currentDate = new Date(System.currentTimeMillis());

            return Objects.nonNull(username) &&
                    Objects.nonNull(roles) &&
                    Objects.nonNull(expirationDate) &&
                    currentDate.before(expirationDate);
        }

        return false;
    }

    private DecodedJWT getDecodedJWT(String authenticationToken) {
        return getJWTVerifier().verify(authenticationToken);
    }

    private JWTVerifier getJWTVerifier() {
        return JWT.require(getSignatureAlgorithm()).build();
    }

    public String getUsername(String authenticationToken) {
        var decodedJWT = getDecodedJWT(authenticationToken);

        if (Objects.nonNull(decodedJWT)) {
            return decodedJWT.getSubject();
        }

        return null;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(String authenticationToken) {
        var decodedJWT = getDecodedJWT(authenticationToken);

        if (Objects.nonNull(decodedJWT)) {
            var roles = decodedJWT.getClaim("roles").asArray(String.class);

            return Stream.of(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        }

        return null;
    }
}
