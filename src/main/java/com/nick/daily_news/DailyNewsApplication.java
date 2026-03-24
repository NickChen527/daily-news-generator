package com.nick.daily_news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(NewsConfig.class)
public class DailyNewsApplication {

  public static void main(String[] args) {
    SpringApplication.run(DailyNewsApplication.class, args);
  }
}
