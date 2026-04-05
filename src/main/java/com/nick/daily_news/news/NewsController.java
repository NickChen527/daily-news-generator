package com.nick.daily_news.news;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewsController {

  private final NewsApiClient client;
  private final NewsConfig config;
  private final NewsService newsService;

  @GetMapping("/news")
  @PreAuthorize("hasRole('MEMBER')")
  public NewsApiResponse getNews() {
    return client.getNews("us", config.getApiKey());
  }

  public void generateEmail() {
    String email = "";

    // 根據用戶感興趣的領域抓相關新聞
    var news = newsService.getNewsByMemberInterest(email);
    // 將這些新聞的內容送給AI做摘要
    // 將生成的摘要填入信件模板
    // 發信
  }
}
