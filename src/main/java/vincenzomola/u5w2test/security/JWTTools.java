package vincenzomola.u5w2test.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vincenzomola.u5w2test.entities.Dipendente;
import vincenzomola.u5w2test.exceptions.UnauthorizedException;

import java.util.Date;

@Component
public class JWTTools {

    private final String secret;

    public JWTTools(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(Dipendente dipendente) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .subject(String.valueOf(dipendente.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();

    }

    public void verifyToken(String token) {

        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi con il token!");
        }

    }

}
