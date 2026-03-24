package com.nick.daily_news;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class NewsResponse {
  private String status;
  private Long totalResults;
  private List<Article> articles;

  @Data
  public static class Article {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private OffsetDateTime publishedAt;
    private String content;

    @Data
    public static class Source {
      private String id;
      private String name;
    }
  }
}
