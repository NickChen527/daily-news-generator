package com.nick.daily_news.member.controller;

import com.nick.daily_news.member.controller.dtos.AuthenticationResponse;
import com.nick.daily_news.member.controller.dtos.LoginRequest;
import com.nick.daily_news.member.controller.dtos.RegisterRequest;
import com.nick.daily_news.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/register")
  public AuthenticationResponse register(@Valid @RequestBody RegisterRequest request) {
    return memberService.register(request);
  }

  @PostMapping("/auth")
  public AuthenticationResponse authenticate(@Valid @RequestBody LoginRequest request) {
    return null;
  }
}
