package com.nick.daily_news.news;

import com.nick.daily_news.member.service.MemberService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsService {

  private final MemberService memberService;
  private final NewsApiClient newsApiClient;
  private final NewsConfig newsConfig;
  private final NewsMapper mapper;

  /** 根據用戶感興趣的領域抓相關新聞 */
  public List<NewsDto> getNewsByMemberInterest(String email) {
    // 取得客戶興趣清單
    var memberVo = memberService.findByEmail(email);
    var interests = memberVo.getInterests();
    // 依序打NewsApi取得新聞，只需要標題、內容、url
    List<NewsDto> newsDto = new ArrayList<>();
    interests.forEach(
        interest -> {
          var newsApiResponse = newsApiClient.getNews(interest.getValue(), newsConfig.getApiKey());
          newsDto.add(mapper.fromNewsApiResponse(newsApiResponse));
        });
    // 回傳
    return newsDto;
  }
}
