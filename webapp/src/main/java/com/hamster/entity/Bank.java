package com.hamster.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

/**
 * Created by opabinia on 2017/5/15.
 */
@ApiModel(value = "银行信息")
public class Bank {

  @Id
  private Long id;
  /**
   * 银行卡英文标识.
   */
  @ApiModelProperty(value = "银行卡英文标识", example = "ICBC")
  private String bank;
  /**
   * 银行卡名称.
   */
  private String name;
  /**
   * logo.
   */
  private String logo;
  /**
   * 银行电话.
   */
  private String tel;
  /**
   * 银行网站.
   */
  private String website;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }
}
