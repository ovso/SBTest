package io.github.ovso.sbtest.main.model;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.*;
/**
 * Created by jaeho on 2018. 3. 21
 */
public class IdConfirmTest {

  @Test public void idConfirmEmptyTrueResult() {
    IdConfirm idConfirm = new IdConfirm();
    assertThat(IdConfirm.isEmptyValue(idConfirm), is(true));
  }

  @Test public void idConfirmEmptyFalseResult() {
    IdConfirm idConfirm = new IdConfirm();
    idConfirm.setSendAmount("1");
    idConfirm.setBankName("1");
    idConfirm.setPayeeAccountNumber("1");
    idConfirm.setPayeeAddress("1");
    idConfirm.setPayeeName("1");
    idConfirm.setRecipientAmount("1");
    idConfirm.setPayeePhone("1");
    assertThat(IdConfirm.isEmptyValue(idConfirm), is(false));
  }
}