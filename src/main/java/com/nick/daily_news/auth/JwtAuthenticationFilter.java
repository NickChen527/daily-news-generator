package com.nick.daily_news.auth;

import com.nick.daily_news.member.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  private final MemberService memberService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;

    // 如果 Header 為空，或者不是以 Bearer 開頭，代表該請求沒有攜帶 Token 或格式錯誤
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      // 呼叫下一個 filter 繼續執行，如果該路徑需要權限，後面的 Filter 會攔截並報錯
      filterChain.doFilter(request, response);
      return;
    }

    jwt = authHeader.substring(7); // 去掉前面的 Bearer_ 第 7 個 char 開始是 jwt 的內容
    userEmail = jwtService.extractUsername(jwt);
    if (userEmail != null
        && SecurityContextHolder.getContext().getAuthentication()
            == null) { // SecurityContextHolder 裡面沒有 Authentication 代表這個 user 還沒登入過
      UserDetails userDetails = this.memberService.loadUserByUsername(userEmail);
      if (jwtService.isTokenValid(jwt, userDetails)) {
        // UsernamePasswordAuthenticationToken 是 Spring Security 用來代表「已認證用戶」的物件，包含了用戶資訊、密碼（通常給
        // null）、以及權限清單
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 將建立好的驗證資訊存入 SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    // 執行後續的過濾器
    filterChain.doFilter(request, response);
  }
}
