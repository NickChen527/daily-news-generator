package com.nick.daily_news.member.service;

import com.nick.daily_news.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** UserDetailsService：Jwt 用 UserDetailsPasswordService：AuthenticationProvider 用 */
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService, UserDetailsPasswordService {

  private final MemberRepository repository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return repository
        .findByEmail(email)
        .orElseThrow(() -> new RuntimeException("找不到 Email 為：" + email + " 的會員資料"));
  }

  @Override
  public UserDetails updatePassword(UserDetails user, String newPassword) {
    return null;
  }
}
