package io.github.ovso.sbtest.main;

import android.content.DialogInterface;
import android.support.annotation.StringRes;

/**
 * Created by jaeho on 2018. 3. 20
 */

public interface MainPresenter {
  void onCreate();

  void onCountryClick();

  void onCurrencyClick();

  void onSendTextChanged(String sendMoneyStr, boolean focused);

  void onRecipientTextChanged(String recipientMoneyStr, boolean focused);

  interface View {
    void setListener();

    void showCurrencyDialog(String[] countries, DialogInterface.OnClickListener onClickListener);

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

    void showViPickupBankName(String pickupBank);

    void showIdBankName(String bank);

    void showViConfirmAccountNumber(String acctNo);

    void showIdConfirmAccountNumber(String acctNo);

    void showViPayeeName(String s);

    void showIdPayeeName(String s);
  }
}
