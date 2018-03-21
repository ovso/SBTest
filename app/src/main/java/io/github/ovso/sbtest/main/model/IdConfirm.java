package io.github.ovso.sbtest.main.model;

import io.github.ovso.sbtest.framework.ObjectUtils;
import lombok.Data;

/**
 * Created by jaeho on 2018. 3. 21
 */

@Data public class IdConfirm {
  private String sendAmount;
  private String recipientAmount;
  private String bankName;
  private String payeeName;
  private String payeeAccountNumber;
  private String payeePhone;
  private String payeeAddress;

  public static boolean isEmptyValue(IdConfirm id) {
    return (isEmpty(id.sendAmount)
        || isEmpty(id.recipientAmount)
        || isEmpty(id.bankName)
        || isEmpty(id.payeeName)
        || isEmpty(id.payeeAccountNumber)
        || isEmpty(id.payeePhone)
        || isEmpty(id.payeeAddress));
  }

  private static boolean isEmpty(String text) {
    return ObjectUtils.isEmpty(text);
  }
}
