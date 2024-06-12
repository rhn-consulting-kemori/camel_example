package com.redhat.example.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class KijitsuNyukinRequestEntity {
  /** 
   * 受付ID
  */
  private String id;

  /** 
   * カード番号
  */
  private String cardbng;

  /** 
   * 個人契約番号
  */
  private String kojinkyknmb;

  /** 
   * 顧客請求締年月日
  */
  private String seiyushimeymd;

  /**
   * 入金年月日
   */
  private String nyukinday;

  /** 
   * 入金額
  */
  private BigDecimal nyukingak;

  /**
   * 過剰金取り扱い区分
   */
  private String kajokintriatukaikbn;
}
