package com.example.book_recode.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

  @RequestMapping(value = {"/"})
  public String index() {
    return "index";
  }
}