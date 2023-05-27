package com.ftn.sbnz.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
public class TokenUtils {
    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    private final String APP_NAME = "sbnz-app";
    private final int EXPIRES_IN = 5 * 60 * 60 * 1000;
    private final String AUTH_HEADER = "Authorization";

    private final String SECRET;
    private final SignatureAlgorithm SIGNATURE_ALGORITHM;
    private final Key SECRET_KEY;

    public TokenUtils(@Value("${secret.key}") String secret) {
        SECRET = secret;

        SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
//        SECRET_KEY = Keys.secretKeyFor(SIGNATURE_ALGORITHM);
        SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
//        String secretString = Encoders.BASE64.encode(SECRET_KEY.getEncoded());
    }

    public String generateToken(String username) {
        Date issuedAt = new Date();
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setAudience(generateAudience())
                .setIssuedAt(issuedAt)
                .setExpiration(generateExpirationDate(issuedAt))
                // .claim("key", value)     // moguce je postavljanje proizvoljnih podataka u telo JWT tokena
                .signWith(SECRET_KEY)
                .compact();
    }

    private String generateAudience() {
//		Moze se iskoristiti org.springframework.mobile.device.Device objekat za odredjivanje tipa uredjaja sa kojeg je zahtev stigao.

//		String audience = AUDIENCE_UNKNOWN;
//		if (device.isNormal()) {
//			audience = AUDIENCE_WEB;
//		} else if (device.isTablet()) {
//			audience = AUDIENCE_TABLET;
//		} else if (device.isMobile()) {
//			audience = AUDIENCE_MOBILE;
//		}
        return AUDIENCE_WEB;
    }

    private Date generateExpirationDate(Date issuedAt) {
        return new Date(issuedAt.getTime() + EXPIRES_IN);
    }

    public String refreshToken(String token) {
        Date issuedAt = new Date();
        final Claims claims = this.getAllClaimsFromToken(token);
        claims.setIssuedAt(issuedAt);

        String refreshedToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate(issuedAt))
                .signWith(SECRET_KEY)
                .compact();
        return refreshedToken;
    }

    public boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = this.getIssuedAtDateFromToken(token);
        return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset))
                && (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        // final Date created = getIssuedAtDateFromToken(token);

        return username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        // && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate());
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    public Date getIssuedAtDateFromToken(String token) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claims.getIssuedAt();
    }

    public String getAudienceFromToken(String token) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claims.getAudience();
    }

    public Date getExpirationDateFromToken(String token) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claims.getExpiration();
    }

    public int getExpiredIn() {
        return EXPIRES_IN;
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);

        // JWT se prosledjuje kroz header Authorization u formatu:
        // Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

    private boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return lastPasswordReset != null && created.before(lastPasswordReset);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private boolean ignoreTokenExpiration(String token) {
        String audience = this.getAudienceFromToken(token);
        return audience.equals(AUDIENCE_TABLET) || audience.equals(AUDIENCE_MOBILE);
    }

    private Claims getAllClaimsFromToken(String token) {
        // Throws SignatureException and ExpiredJwtException if token is not valid
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);

        // Exception is not thrown, therefore everything is ok with jwt token
        return claimsJws.getBody();
    }
}
