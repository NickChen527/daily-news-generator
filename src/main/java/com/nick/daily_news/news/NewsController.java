package com.nick.daily_news.news;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewsController {

  private final NewsApiClient client;
  private final NewsConfig config;

  @GetMapping("/news")
  public NewsResponse getNews() {
    return client.getNews("us", config.getApiKey());
  }
}
