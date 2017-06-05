package com.hamster.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by opabinia on 2017/5/11.
 */
@Controller
@RequestMapping("/")
@Api(value = "例子", description = "例子描述")
public class WelcomeController {

  private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);

//  @RequestMapping(value = "/", method = RequestMethod.GET)
//  @ApiOperation(value = "首页", response = String.class)
//  @ResponseBody
//  public String home() {
//    log.info("test log...");
//    return "Hello World";
//  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ApiOperation("首页")
  public String index(
      @RequestParam(value = "skin", required = false, defaultValue = "default") String skin,
      Model model) {
    model.addAttribute("skin", skin);
    return "index";
  }

  @RequestMapping(value = "/menu", method = RequestMethod.GET)
  @ApiOperation("菜单页面")
  public String menu() {
    return "menu";
  }

  @RequestMapping(value = "/main", method = RequestMethod.GET)
  @ApiOperation("主窗口")
  public String main() {
    return "main";
  }

}
