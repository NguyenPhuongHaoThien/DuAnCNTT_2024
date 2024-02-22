package tdtu.edu.vn.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtilsHelper {

    private SecretKey key;

    // Loại bỏ @Value cho privateKey vì chúng ta sẽ tạo khóa một cách an toàn
    @Value("${jwt.expiration}")
    private long expiration; // Đổi thành long để hỗ trợ thời gian lớn

    @PostConstruct
    public void init() {
        // Tạo khóa an toàn cho HS512
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(String subject) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean verifyToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Ghi log lỗi ở đây hoặc xử lý tùy chỉnh
            return false;
        }
    }
}
