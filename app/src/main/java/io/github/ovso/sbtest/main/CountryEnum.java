package io.github.ovso.sbtest.main;

import lombok.Getter;

/**
 * Created by jaeho on 2018. 3. 20
 */

public enum CountryEnum {
  VI(0, "베트남", "VDN", 0.047, 21.2765957), ID(1, "인도네시아", "IDR", 0.078, 0);

  @Getter private String country, currency;
  @Getter private double exchangeRate, exchangeRate2;
  @Getter private int type;

  private CountryEnum(int type, String country, String currency, double exchangeRate,
      double exchangeRate2) {
    this.type = type;
    this.country = country;
    this.currency = currency;
    this.exchangeRate = exchangeRate;
    this.exchangeRate2 = exchangeRate2;
  }
}