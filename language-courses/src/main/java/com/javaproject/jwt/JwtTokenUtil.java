package com.javaproject.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.javaproject.admin.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
	// thời gian hết hạn của token
	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour
    
    @Value("${app.jwt.secret}")
    private String secretKey;
    
    // tạo access token từ thông tin của user sau khi authentication thành công
    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getEmail())) // dạng: id, email
                .setIssuer("Edukate")
                .claim("role", user.getRole().toString())
                .setIssuedAt(new Date()) // thời gian hiện tại
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION)) // thời gian hết hạn
                .signWith(SignatureAlgorithm.HS512, secretKey) // mã hóa secretKey bằng thuật toán HS512
                .compact();
                 
    }
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    
    // kiểm tra secretKey
    // nếu lỗi sẽ hiển thị log ở console
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }
         
        return false;
    }
    
    // lấy giá trị của "sub" từ token
    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }
     
    // lấy phần payload từ token
    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
