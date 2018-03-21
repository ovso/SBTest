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

    void showViConfirmPickupBankNameLable(String pickupBank);

    void showIdConfirmBankNameLable(String bank);

    void showIdConfirmAccountNumberLable(String acctNo);

    void showViConfirmPayeeFirstNameLable(String s);

    void showIdConfirmPayeeFirstNameLable(String s);

    void showViConfirmPayeeLastNameLable(String lastName);

    void showIdConfirmPayeeLastnameLable(String lastName);

    void showViConfirmPayeePhoneLable(String mobile);

    void showIdConfirmPayeePhoneLable(String mobile);

    void showViConfirmSendAmountLable(String sendAmount);

    void showViConfirmRecipientAmountLable(String receiveAmount);

    void showIdConfirmSendAmountLable(String sendAmount);

    void showIdConfirmRecipientAmountLable(String receiveAmount);

    void showIdConfirmPayeeAddressLabel(String address);
  }
}
