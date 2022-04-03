package com.book.record.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class BookCheckController {

  @Value("${naver_clientID}")
  public static String clientId;

  @Value("${naver_cliendSK}")
  public static String clientSecret;

  public static List<String> getBookTitle(String[] isbnArray) throws IOException {
    List<String> titleArray = null;

    for (int i=0; i<isbnArray.length; i++){
      String apiURL = "https://openapi.naver.com/v1/search/book.json?d_isbn=" + isbnArray[i];

      Map<String, String> requestHeaders = new HashMap<>();
      requestHeaders.put("X-Naver-Client-Id", clientId);
      requestHeaders.put("X-Naver-Client-Secret", clientSecret);
      String title = get(apiURL,requestHeaders);

      System.out.println(title);
      titleArray.add(title);
    }

    return titleArray;
  }


  private static String get(String apiURL, Map<String, String> requestHeaders) throws IOException {
    URL url = new URL(apiURL);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    try {
      con.setRequestMethod("GET");
      for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
        con.setRequestProperty(header.getKey(), header.getValue());
      }

      int responseCode = con.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
        return readBody(con.getInputStream());
      } else { // 에러 발생
        return readBody(con.getErrorStream());
      }
    } catch (IOException e) {
      throw new RuntimeException("API 요청과 응답 실패", e);
    } finally {
      con.disconnect();
    }
  }

  private static String readBody(InputStream body){
    InputStreamReader streamReader = new InputStreamReader(body);
    try (BufferedReader lineReader = new BufferedReader(streamReader)) {
      StringBuilder responseBody = new StringBuilder(); String line;
      while ((line = lineReader.readLine()) != null) {
        responseBody.append(line);
      }
      return responseBody.toString();
    } catch (IOException e) {
      throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e); }
  }
}
