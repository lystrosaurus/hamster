package com.hamster.controller;

import com.google.common.collect.ImmutableMap;
import com.hamster.entity.Bank;
import com.hamster.service.BankRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by opabinia on 2017/5/15.
 */
@RestController
@RequestMapping("/bank")
@Api(value = "银行信息", description = "测试银行信息获取")
public class BankController {
  private static final String SUCCESS = "success";
  private String uri = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=%s&cardBinCheck=true";
  @Autowired
  private BankRepository bankRepository;

  /**
   * 根据银行卡号查询银行卡信息.
   * @param cardNo 银行卡号
   * @return 银行卡信息
   */
  @RequestMapping(
      value = "/query/{cardNo}",
      method = RequestMethod.GET,
      produces = "application/json")
  @ApiOperation(value = "查询银行信息")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "200"), // Successfully retrieved list
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403,
          message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  public Map<String, Object> query(
      @ApiParam(name = "cardNo", value = "银行卡号",
      defaultValue = "6228481698729890079", example = "6228481698729890079")
      @PathVariable String cardNo) {
    RestTemplate restTemplate = new RestTemplate();
    // 只是测试
    Map result = new HashMap();
    ResponseEntity<Map> resp = restTemplate.getForEntity(String.format(uri, cardNo), Map.class);
    if (resp.getStatusCode() == HttpStatus.OK) {
      Map data = new HashMap();
      Map map = resp.getBody();
      String bankName = (String) map.get("bank");
      Bank bank = bankRepository.findByBank(bankName);
      if (bank != null) {
        data.put("bank", map.get("bank"));
        data.put("name", bank.getName());
        data.put("logo", bank.getLogo());
        result.put("result", ImmutableMap.of("data", data, SUCCESS, true));
      }
    } else {
      result.put("result", ImmutableMap.of(SUCCESS, false));
    }

    return result;
  }

}
