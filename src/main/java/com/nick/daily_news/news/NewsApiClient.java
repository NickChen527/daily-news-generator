package com.nick.daily_news.news;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "newsClient")
public interface NewsApiClient {

  @GetMapping("/top-headlines")
  public NewsResponse getNews(
      @RequestParam("country") String country, @RequestParam("apiKey") String apiKey);
}
