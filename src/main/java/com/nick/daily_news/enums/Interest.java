package com.nick.daily_news.enums;

import lombok.Getter;

@Getter
public enum Interest {
  BUSINESS("business"),
  ENTERTAINMENT("entertainment"),
  GENERAL("general"),
  HEALTH("health"),
  SCIENCE("science"),
  SPORTS("sports"),
  TECH("technology");

  private final String value;

  Interest(String value) {
    this.value = value;
  }
}
