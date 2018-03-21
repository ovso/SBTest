package io.github.ovso.sbtest.main.model;

import io.github.ovso.sbtest.framework.ObjectUtils;
import lombok.Data;

/**
 * Created by jaeho on 2018. 3. 21
 */

@Data public class ViConfirm {
  private String sendAmount;
  private String recipientAmount;
  private String pickupBankName;
  private String payeeName;
  private String payeePhone;

  public static boolean isEmptyValue(ViConfirm viConfirm) {
    return (isEmpty(viConfirm.sendAmount) || isEmpty(viConfirm.recipientAmount) || isEmpty(
        viConfirm.pickupBankName) || isEmpty(viConfirm.payeeName) || isEmpty(
        viConfirm.payeePhone));
  }

  private static boolean isEmpty(String text) {
    return ObjectUtils.isEmpty(text);
  }
}
