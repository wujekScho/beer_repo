package pl.wujekscho.beer.security;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import pl.wujekscho.beer.security.entity.User;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.stream.Collectors;

public class TokenUtils {
    private static final long EXPIRES_IN_SECONDS = 300;

    private TokenUtils() {
    }

    public static String generateTokenString(User authenticated) throws Exception {
        String kid = "/privateKey.pem";
        PrivateKey pk = readPrivateKey(kid);

        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer("piotr.schodzinski");
        claims.upn(authenticated.getLogin());
        claims.groups(authenticated.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet()));

        long currentTimeInSecs = currentTimeInSecs();
        long exp = currentTimeInSecs + EXPIRES_IN_SECONDS;

        claims.issuedAt(currentTimeInSecs);
        claims.claim(Claims.auth_time.name(), currentTimeInSecs);
        claims.expiresAt(exp);

        return claims.jws().signatureKeyId(kid).sign(pk);
    }

    public static String refreshToken(JsonWebToken token) throws Exception {
        String kid = "/privateKey.pem";
        PrivateKey pk = readPrivateKey(kid);

        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer("piotr.schodzinski");
        claims.upn(token.getName());
        claims.groups(token.getGroups());

        long currentTimeInSecs = currentTimeInSecs();
        long exp = currentTimeInSecs + EXPIRES_IN_SECONDS;

        claims.issuedAt(currentTimeInSecs);
        claims.claim(Claims.auth_time.name(), currentTimeInSecs);
        claims.expiresAt(exp);

        return claims.jws().signatureKeyId(kid).sign(pk);
    }

    private static PrivateKey readPrivateKey(final String pemResName) throws Exception {
        try (InputStream contentIS = TokenUtils.class.getResourceAsStream(pemResName)) {
            byte[] tmp = new byte[4096];
            int length = contentIS.read(tmp);
            return decodePrivateKey(new String(tmp, 0, length, StandardCharsets.UTF_8));
        }
    }

    private static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
        byte[] encodedBytes = toEncodedBytes(pemEncoded);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    private static byte[] toEncodedBytes(final String pemEncoded) {
        final String normalizedPem = removeBeginEnd(pemEncoded);
        return Base64.getDecoder().decode(normalizedPem);
    }

    private static String removeBeginEnd(String pem) {
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
        pem = pem.replaceAll("-----END (.*)----", "");
        pem = pem.replaceAll("\r\n", "");
        pem = pem.replaceAll("\n", "");
        return pem.trim();
    }

    public static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }
}
