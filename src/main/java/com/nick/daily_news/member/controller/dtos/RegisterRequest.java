package com.nick.daily_news.member.controller.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
  /** 會員email */
  @Email(message = "請提供有效的 email 格式")
  private String email;

  /** 手機號碼 */
  private Long phone;

  /** 密碼 */
  @NotBlank(message = "密碼不能為空")
  @Size(min = 6, message = "密碼至少需要 6 個字元")
  private String password;

  /** 會員名稱 */
  @NotBlank(message = "名稱不能為空")
  private String fullName;
}
