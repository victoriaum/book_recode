package com.book.record.web;

import com.book.record.service.RecordService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class MainController{
  public final RecordService recordService;

  @RequestMapping(value = {"/"})
  public String index() {
    return "index";
  }

  /*List<RecordDto> recordDtoList = recordService.bookCount(date,member);*/

  @RequestMapping(value = {"/index"})
  public String index(@RequestParam("date") String date, @RequestParam("member") String member
                      , Model m) throws ParseException {
    String[] dateArray = date.split("-");
    if("all".equals(member)){
      member = "All";
    }
    m.addAttribute("filter",dateArray[0]+" "+dateArray[1]+" "+member);

    Date today = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    System.out.println(today);

    // 모든 회원에 대한 정보를 표기하는 경우
    if("All".equals(member)){
      Date openDate = formatter.parse("2021/09/08");
      long diffSec = (today.getTime() - openDate.getTime()) / 1000;
      long diffDays = diffSec / (24*60*60);

      int totalBooks = recordService.totalBooks(date);
      int memberCount = recordService.memberCount(date);

      m.addAttribute("totalBooks",totalBooks);
      m.addAttribute("memberCount",memberCount);
      m.addAttribute("diffDays",diffDays);
    }
    else {  // 특정 회원에 대한 정보를 표기하는 경우

    }
    return "index";
  }

  @RequestMapping(value = {"/book"})
  public String book() {

    return "book";
  }
}