package com.nick.daily_news.member.repository.entity;

import com.nick.daily_news.enums.Interest;
import com.nick.daily_news.enums.Role;
import com.nick.daily_news.enums.Status;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/** 會員表之entity */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBER")
public class MemberEntity implements UserDetails {

  /** 會員ID */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  /** 會員email */
  @Column(name = "EMAIL")
  private String email;

  /** 手機號碼 */
  @Column(name = "PHONE")
  private Long phone;

  /** 密碼(加密) */
  @Column(name = "PASSWORD")
  private String password;

  /** 會員名稱 */
  @Column(name = "FULL_NAME")
  private String fullName;

  /** 註冊時間 */
  @Column(name = "REGISTER_DATE")
  private LocalDateTime registerDate;

  /** 此欄位紀錄會員的角色，用於 Spring Security 驗證 */
  @Column(name = "ROLE")
  @Enumerated(value = EnumType.STRING)
  private Role role;

  /** 啟用狀態 */
  @Column(name = "STATUS")
  @Enumerated(value = EnumType.STRING)
  private Status status;

  /** 感興趣領域 */
  @Column(name = "INTERESTS")
  private List<Interest> interests;

  // 以下是 UserDetails 的方法
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return Status.ACTIVE.equals(status);
  }
}
