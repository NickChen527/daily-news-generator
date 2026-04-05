package com.nick.daily_news.ai;

import com.nick.daily_news.news.NewsDto;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AiService {

  private final GoogleAiGeminiChatModel chatModel;

  public String modelTest(String msg) {
    return chatModel.chat(msg);
  }

  public void generateNewsSummary(List<NewsDto> newsDto) {
    // 系統提示詞
    // 傳入News資料給AI，讓他生成總結
    //

  }
}
