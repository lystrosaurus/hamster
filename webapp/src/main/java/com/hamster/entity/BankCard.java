package com.hamster.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by opabinia on 2017/5/16.
 */
public class BankCard {

  @Id
  private Long id;
  /**
   * BIN 号 Bank card number或Bank Identification Number
   * Issuer Identification Number，缩写为IIN.
   */
  private String bin;
  /**
   * 银行卡名称.
   */
  private String bankName;
  /**
   * 卡代码.
   */
  private String cardCode;
  /**
   * 卡种名称.
   */
  private String cardName;
  /**
   * 卡类型.
   */
  private String cardType;
  /**
   * 卡号长度.
   */
  private Long length;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBin() {
    return bin;
  }

  public void setBin(String bin) {
    this.bin = bin;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getCardCode() {
    return cardCode;
  }

  public void setCardCode(String cardCode) {
    this.cardCode = cardCode;
  }

  public String getCardName() {
    return cardName;
  }

  public void setCardName(String cardName) {
    this.cardName = cardName;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public Long getLength() {
    return length;
  }

  public void setLength(Long length) {
    this.length = length;
  }
}
