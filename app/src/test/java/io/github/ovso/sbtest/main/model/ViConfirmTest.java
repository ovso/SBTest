package io.github.ovso.sbtest.main.model;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;
/**
 * Created by jaeho on 2018. 3. 21
 */
public class ViConfirmTest {

  @Test public void viConfirmEmptyTrueResult() {
    ViConfirm viConfirm = new ViConfirm();
    viConfirm.setRecipientAmount("1000");
    boolean isEmpty = ViConfirm.isEmptyValue(viConfirm);
    assertThat(isEmpty, is(true));
  }

  @Test public void viConfirmEmptyFalseResult() {
    ViConfirm viConfirm = new ViConfirm();
    viConfirm.setRecipientAmount("1");
    viConfirm.setSendAmount("1");
    viConfirm.setPickupBankName("1");
    viConfirm.setPayeeName("1");
    viConfirm.setPayeePhone("1");
    boolean isEmpty = ViConfirm.isEmptyValue(viConfirm);
    assertThat(isEmpty, is(false));
  }
}