package com.hamster.util;

import com.hamster.robot.RobotEnum;
import com.hamster.robot.TlRobotFactory;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by opabinia on 2017/5/26.
 */
public final class RobotUtil {

  private RobotUtil() {
  }

  /**
   * 发送消息.
   * @param params 消息参数
   * @param robotEnum 机器人类型枚举
   * @return 机器人回复内容
   * @throws UnsupportedEncodingException UnsupportedEncodingException
   */
  public static String send(Map<String, Object> params, RobotEnum robotEnum)
      throws UnsupportedEncodingException {
    String result = null;
    switch (robotEnum) {
      case TL:
        result = new TlRobotFactory().createRobot().send(params);
        break;
      default:
        break;
    }
    return result;
  }
}
