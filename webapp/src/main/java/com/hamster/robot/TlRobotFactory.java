package com.hamster.robot;

/**
 * Created by opabinia on 2017/5/26.
 */
public class TlRobotFactory implements RobotFactory {

  @Override
  public Robot createRobot() {
    return new TlRobot();
  }
}
