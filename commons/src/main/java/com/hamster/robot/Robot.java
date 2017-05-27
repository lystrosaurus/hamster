package com.hamster.robot;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by opabinia on 2017/5/26.
 */
public interface Robot {
    /**
     * 发送消息
     * @param body 消息体
     * @return 消息回复
     */
    String send(Map<String, Object> body) throws UnsupportedEncodingException;
}
