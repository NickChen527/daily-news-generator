package com.nick.daily_news;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "news")
public class NewsConfig {
  private String apiKey;
}
