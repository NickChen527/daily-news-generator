package com.nick.daily_news.member.service;

import com.nick.daily_news.auth.JwtService;
import com.nick.daily_news.enums.Role;
import com.nick.daily_news.enums.Status;
import com.nick.daily_news.member.controller.dtos.AuthenticationResponse;
import com.nick.daily_news.member.controller.dtos.RegisterRequest;
import com.nick.daily_news.member.exception.EmailAlreadyExistsException;
import com.nick.daily_news.member.repository.MemberRepository;
import com.nick.daily_news.member.repository.entity.MemberEntity;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** UserDetailsService：Jwt 用 UserDetailsPasswordService：AuthenticationProvider 用 */
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService, UserDetailsPasswordService {

  private final MemberRepository repository;
  private final PasswordEncoder encoder;
  private final JwtService jwtService;

  /**
   * 註冊新會員
   *
   * @throws EmailAlreadyExistsException 若 email 已被註冊
   */
  public AuthenticationResponse register(RegisterRequest request) {
    // 檢查 email 是否已存在
    if (repository.findByEmail(request.getEmail()).isPresent()) {
      throw new EmailAlreadyExistsException("此 email 已被註冊，請直接登入或使用其他 email");
    }

    // 建立新會員，預設狀態為 ACTIVE
    MemberEntity member =
        MemberEntity.builder()
            .email(request.getEmail())
            .password(encoder.encode(request.getPassword()))
            .phone(request.getPhone())
            .fullName(request.getFullName())
            .role(Role.MEMBER)
            .status(Status.ACTIVE)
            .registerDate(LocalDateTime.now())
            .build();

    repository.save(member);

    // 產生並回傳 JWT token
    String jwtToken = jwtService.generateToken(member);
    return AuthenticationResponse.builder().jwtToken(jwtToken).build();
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return repository
        .findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("找不到 email 為：" + email + " 的會員"));
  }

  @Override
  public UserDetails updatePassword(UserDetails user, String newPassword) {
    MemberEntity member =
        repository
            .findByEmail(user.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("找不到會員"));
    member.setPassword(encoder.encode(newPassword));
    return repository.save(member);
  }
}
