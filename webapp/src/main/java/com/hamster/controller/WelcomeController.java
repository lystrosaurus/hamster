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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by opabinia on 2017/5/11.
 */
@Controller
@RequestMapping("/")
@Api(value = "例子", description = "例子描述")
public class WelcomeController {

  private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ApiOperation(value = "首页", response = String.class)
  @ResponseBody
  public String home() {
    log.info("test log...");
    return "Hello World";
  }

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index(
      @RequestParam(value = "name", required = false, defaultValue = "World") String name,
      Model model) {
    model.addAttribute("name", name);
    return "index";
  }
}
