package com.nick.daily_news.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/** 此類別封裝有關驗證、產生Jwt的方法實作 */
@Service
public class JwtService {

  /**
   * 因為 @ConfigurationProperties() 這個註解會透過 Setter 方法來附值，但如果要用 Setter 就不能把欄位設為 final 這會影響 secret key
   * 的安全性，所以決定採用 final + @Value()
   */
  private final String secretKey;

  /**
   * 因為 @RequiredArgsConstructor 產生的 Constructor 預設不會透過 @Value 抓設定檔的資料，所以這邊手寫 之後可以考慮做一個 lombok 的
   * config 來解決此問題
   */
  public JwtService(@Value("${auth.secret-key}") String secretKey) {
    this.secretKey = secretKey;
  }

  /**
   * 此方法將 username 從 Jwt 中解析出來
   *
   * @param jwt Jwt
   * @return username
   */
  public String extractUsername(String jwt) {
    return extractClaim(jwt, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
        .signWith(getSignInKey())
        .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  /** 此方法... */
  public Claims extractAllClaims(String jwt) {
    return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
