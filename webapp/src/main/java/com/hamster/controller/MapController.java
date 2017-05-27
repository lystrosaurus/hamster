package com.hamster.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.ImmutableMap;
import com.hamster.map.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by opabinia on 2017/5/26.
 */
@RestController
@RequestMapping("map")
public class MapController {

  private static final Logger log = LoggerFactory.getLogger(MapController.class);

  /**
   * 获取城市天气信息.
   *
   * @param city 城市名称
   * @return 天气信息
   */
  @RequestMapping("weather/weatherInfo")
  public ResponseEntity weatherInfo(
      @RequestParam(defaultValue = "上海", required = false) String city) {
    RestTemplate restTemplate = new RestTemplate();
    StringBuilder url = new StringBuilder(Constant.A_MAP_WEATHER_URL);
    url.append("key=")
        .append(Constant.A_MAP_KEY)
        .append("&city=")
        .append(city);
    ResponseEntity<String> resp = restTemplate
        .getForEntity(url.toString(), String.class);

    if (log.isInfoEnabled()) {
      log.info(JSONObject.toJSONString(resp, SerializerFeature.PrettyFormat));
    }

    return new ResponseEntity(ImmutableMap.of("weatherInfo", JSONObject.parse(resp.getBody())),
        HttpStatus.OK);
  }
}
