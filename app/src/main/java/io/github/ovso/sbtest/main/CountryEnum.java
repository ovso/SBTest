package io.github.ovso.sbtest.main;

import lombok.Getter;

/**
 * Created by jaeho on 2018. 3. 20
 */

public enum CountryEnum {
  VI(0, "베트남(Vietnam)", "VND", 0.047), ID(1, "인도네시아(Indonesia)", "IDR", 0.078);

  @Getter private String country, currency;
  @Getter private double exchangeRate;
  @Getter private int type;

  private CountryEnum(int type, String country, String currency, double exchangeRate) {
    this.type = type;
    this.country = country;
    this.currency = currency;
    this.exchangeRate = exchangeRate;
  }
}