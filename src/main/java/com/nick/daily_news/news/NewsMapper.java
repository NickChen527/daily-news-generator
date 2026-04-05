package com.nick.daily_news.news;

import org.mapstruct.Mapper;

@Mapper(componentModel = "SPRING")
public interface NewsMapper {

  NewsDto fromNewsApiResponse(NewsApiResponse response);
}
