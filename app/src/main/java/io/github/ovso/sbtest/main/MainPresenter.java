package io.github.ovso.sbtest.main;

import android.content.DialogInterface;
import android.support.annotation.StringRes;
import io.github.ovso.sbtest.main.model.IdConfirm;
import io.github.ovso.sbtest.main.model.ViConfirm;

/**
 * Created by jaeho on 2018. 3. 20
 */

public interface MainPresenter {
  void onCreate();

  void onCountryClick();

  void onCurrencyClick();

  void onSendTextChanged(String sendMoneyStr, boolean focused);

  void onRecipientTextChanged(String recipientMoneyStr, boolean focused);

  void onViConfirmClick(ViConfirm viConfirm);

  void onIdConfirmClick(IdConfirm idConfirm);

  interface View {
    void setListener();

    void showCurrencyDialog(String[] countries, int checked, DialogInterface.OnClickListener onClickListener);

    void showMessage(String msg);
    void showMessage(@StringRes int resId);

    void showCurrency(String name);

    void showCountry(String country);

    void showRecipientMoney(long recipient);
    void showSendMoney(long send);

    void requestRecipientFocus();

    void showViLayout();
    void showIdLayout();

    void showViConfirmSendAmount(String sendMoneyStr);

    void showViConfirmRecipientAmount(String money);

    void showIdConfirmSendAmount(String sendMoneyStr);

    void showIdConfirmRecipientAmount(String s);

    void showViConfirmPickupBankNameLable(String pickupBank);

    void showIdConfirmBankNameLable(String bank);

    void showIdConfirmAccountNumberLable(String label);

    void showViConfirmPayeeNameLabel(String label);

    void showIdConfirmPayeeNameLabel(String label);

    void showViConfirmPayeePhoneLable(String label);

    void showIdConfirmPayeePhoneLable(String label);

    void showViConfirmSendAmountLable(String label);

    void showViConfirmRecipientAmountLable(String label);

    void showIdConfirmSendAmountLable(String label);

    void showIdConfirmRecipientAmountLable(String label);

    void showIdConfirmPayeeAddressLabel(String label);

    void showEmptyMessage();

    void showConfirmDialog(String title, String text);
  }
}
