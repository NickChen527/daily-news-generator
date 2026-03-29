package com.nick.daily_news.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

  @PostMapping("/members/register")
  public void register() {}

  @PostMapping("/members/auth")
  public void authenticate() {}
}
