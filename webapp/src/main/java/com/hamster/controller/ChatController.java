package com.hamster.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.hamster.robot.RobotEnum;
import com.hamster.util.RobotUtil;
import java.io.UnsupportedEncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by opabinia on 2017/5/24.
 */
@Controller
@RequestMapping("chat")
public class ChatController {

  private static final Logger log = LoggerFactory.getLogger(ChatController.class);

  @RequestMapping("/")
  public String index() {
    return "robot/robot";
  }

  /**
   * 发送一条文本消息.
   *
   * @param message 消息内容 默认'您好'
   * @return 对消息的回复
   */
  @RequestMapping(value = "/send", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity send(
      @RequestParam(value = "message", required = false, defaultValue = "您好") String message) {
    String repay = null;
    try {
      repay = RobotUtil.send(ImmutableMap.of("message", message), RobotEnum.TL);
      if (log.isInfoEnabled()) {
        log.info(repay);
      }
    } catch (UnsupportedEncodingException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage());
      }
    }

    return new ResponseEntity(ImmutableMap.of("robot", JSONObject.parse(repay)), HttpStatus.OK);
  }
}
