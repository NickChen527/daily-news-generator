package com.nick.daily_news.member.service;

import com.nick.daily_news.enums.Interest;
import com.nick.daily_news.enums.Role;
import com.nick.daily_news.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class MemberVo {
  /** 會員ID */
  private Long id;

  /** 會員email */
  private String email;

  /** 手機號碼 */
  private Long phone;

  /** 密碼(加密) */
  private String password;

  /** 會員名稱 */
  private String fullName;

  /** 註冊時間 */
  private LocalDateTime registerDate;

  /** 此欄位紀錄會員的角色，用於 Spring Security 驗證 */
  private Role role;

  /** 啟用狀態 */
  private Status status;

  /** 感興趣領域 */
  private List<Interest> interests;
}
