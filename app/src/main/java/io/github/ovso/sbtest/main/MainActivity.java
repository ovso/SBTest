package io.github.ovso.sbtest.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import hugo.weaving.DebugLog;
import io.github.ovso.sbtest.R;
import io.github.ovso.sbtest.framework.customview.BaseActivity;
import io.github.ovso.sbtest.main.model.IdConfirm;
import io.github.ovso.sbtest.main.model.ViConfirm;
import javax.inject.Inject;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainPresenter.View {
  //공통
  @Inject MainPresenter presenter;
  @BindView(R.id.country_textview) TextView countryTextView;
  @BindView(R.id.currency_button) TextView currencyButton;
  @BindView(R.id.send_amount_edittext) EditText sendEditText;
  @BindView(R.id.recipient_amount_edittext) EditText recipientEditText;

  //vi layout
  @BindView(R.id.vi_confirm_container) ViewGroup viConfirmContainer;
  @BindView(R.id.vi_confirm_send_amount_textview) TextView viConfirmSendAmountTextView;
  @BindView(R.id.vi_confirm_send_amount_label_textview) TextView viConfirmSendAmountLabelTextView;
  @BindView(R.id.vi_confirm_recipient_amount_textview) TextView viConfirmRecipientAmountTextView;
  @BindView(R.id.vi_confirm_recipient_amount_label_textview) TextView
      viConfirmRecipientAmountLabelTextView;
  @BindView(R.id.vi_confirm_pickup_bank_name_edittext) EditText viConfirmPickupBankNameEditText;
  @BindView(R.id.vi_confirm_pickup_bank_name_label_textview) TextView
      viConfirmPickupBankNameLableTextView;
  @BindView(R.id.vi_confirm_payee_name_label_textview) TextView viConfirmPayeeNameLabelTextView;
  @BindView(R.id.vi_confirm_payee_name_edittext) TextView viConfirmPayeeNameEditText;
  @BindView(R.id.vi_confirm_payee_phone_label_textview) TextView viConfirmPayeePhoneLabelTextView;
  @BindView(R.id.vi_confirm_payee_phone_edittext) EditText viConfirmPayeePhoneEditText;

  //id layout
  @BindView(R.id.id_confirm_container) ViewGroup idConfirmContainer;
  @BindView(R.id.id_confirm_send_amount_textview) TextView idConfirmSendAmountTextView;
  @BindView(R.id.id_confirm_send_amount_label_textview) TextView idConfirmSendAmountLabelTextView;
  @BindView(R.id.id_confirm_recipient_amount_textview) TextView idConfirmRecipientAmountTextView;
  @BindView(R.id.id_confirm_recipient_amount_lable_textview) TextView
      idConfirmRecipientAmountLabelTextView;
  @BindView(R.id.id_confirm_bank_name_edittext) TextView idConfirmBankNameEditText;
  @BindView(R.id.id_confirm_bank_name_label_textview) TextView idConfirmBankNameLabelTextView;
  @BindView(R.id.id_confirm_payee_name_label_textview) TextView idConfirmPayeeNameLabelTextView;
  @BindView(R.id.id_confirm_payee_name_edittext) EditText idConfirmPayeeNameEditText;
  @BindView(R.id.id_confirm_payee_account_number_label_textview) TextView
      idConfirmPayeeAccountNumberLabelTextView;
  @BindView(R.id.id_confirm_payee_account_number_edittext) EditText
      idConfirmPayeeAccountNumberEditText;
  @BindView(R.id.id_confirm_payee_phone_label_textview) TextView idConfirmPayeePhoneLabelTextView;
  @BindView(R.id.id_confirm_payee_phone_edittext) EditText idConfirmPayeePhoneEditText;
  @BindView(R.id.id_confirm_payee_address_label_textview) TextView
      idConfirmPayeeAddressLabelTextView;
  @BindView(R.id.id_confirm_payee_address_edittext) EditText idConfirmPayeeAddressEditText;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate();
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override public void setListener() {

  }

  @Override
  public void showCurrencyDialog(String[] names, int checkedItem, DialogInterface.OnClickListener onClickListener) {
    new AlertDialog.Builder(this).setTitle("국가 설정")
        .setSingleChoiceItems(names, checkedItem, onClickListener)
        .show();
  }

  @Override public void showMessage(String msg) {
    new AlertDialog.Builder(this).setTitle("!!")
        .setMessage(msg)
        .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
          dialogInterface.dismiss();
          finish();
        })
        .show();
  }

  @Override public void showMessage(@StringRes int resId) {
    new AlertDialog.Builder(this).setTitle("!!")
        .setMessage(resId)
        .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
          dialogInterface.dismiss();
          finish();
        })
        .show();
  }

  @Override public void showCurrency(String currency) {
    currencyButton.setText(currency);
  }

  @Override public void showCountry(String country) {
    countryTextView.setText(country);
  }

  @Override public void showRecipientMoney(long recipient) {
    recipientEditText.setText(String.valueOf(recipient));
  }

  @Override public void showSendMoney(long send) {
    sendEditText.setText(String.valueOf(send));
  }

  @Override public void requestRecipientFocus() {
    //recipientEditText.requestFocus();
    Timber.d("recipientFocus = " + recipientEditText.isFocused());
    if (recipientEditText.isFocused()) {
      presenter.onRecipientTextChanged(recipientEditText.getText().toString(),
          recipientEditText.isFocused());
    } else if (sendEditText.isFocused()) {
      presenter.onSendTextChanged(sendEditText.getText().toString(), sendEditText.isFocused());
    }
  }

  @Override public void showViLayout() {
    viConfirmContainer.setVisibility(View.VISIBLE);
    idConfirmContainer.setVisibility(View.GONE);
  }

  @Override public void showIdLayout() {
    idConfirmContainer.setVisibility(View.VISIBLE);
    viConfirmContainer.setVisibility(View.GONE);
  }

  @Override public void showViConfirmSendAmount(String sendMoneyStr) {
    viConfirmSendAmountTextView.setText(sendMoneyStr);
  }

  @Override public void showViConfirmRecipientAmount(String money) {
    viConfirmRecipientAmountTextView.setText(money);
  }

  @Override public void showIdConfirmSendAmount(String sendMoneyStr) {
    idConfirmSendAmountTextView.setText(sendMoneyStr);
  }

  @Override public void showIdConfirmRecipientAmount(String s) {
    idConfirmRecipientAmountTextView.setText(s);
  }

  @Override public void showViConfirmPickupBankNameLable(String pickupBank) {
    viConfirmPickupBankNameLableTextView.setText(pickupBank);
  }

  @Override public void showIdConfirmBankNameLable(String label) {
    idConfirmBankNameLabelTextView.setText(label);
  }

  @Override public void showIdConfirmAccountNumberLable(String label) {
    idConfirmPayeeAccountNumberLabelTextView.setText(label);
  }

  @Override public void showViConfirmPayeeNameLabel(String label) {
    viConfirmPayeeNameLabelTextView.setText(label);
  }

  @Override public void showIdConfirmPayeeNameLabel(String label) {
    idConfirmPayeeNameLabelTextView.setText(label);
  }

  @Override public void showViConfirmPayeePhoneLable(String label) {
    viConfirmPayeePhoneLabelTextView.setText(label);
  }

  @Override public void showIdConfirmPayeePhoneLable(String label) {
    idConfirmPayeePhoneLabelTextView.setText(label);
  }

  @Override public void showViConfirmSendAmountLable(String label) {
    viConfirmSendAmountLabelTextView.setText(label);
  }

  @Override public void showViConfirmRecipientAmountLable(String label) {
    viConfirmRecipientAmountLabelTextView.setText(label);
  }

  @Override public void showIdConfirmSendAmountLable(String label) {
    idConfirmSendAmountLabelTextView.setText(label);
  }

  @Override public void showIdConfirmRecipientAmountLable(String label) {
    idConfirmRecipientAmountLabelTextView.setText(label);
  }

  @Override public void showIdConfirmPayeeAddressLabel(String address) {
    idConfirmPayeeAddressLabelTextView.setText(address);
  }

  @OnClick(R.id.country_textview) void onCountryClick() {
    presenter.onCountryClick();
  }

  @OnClick(R.id.currency_button) void onCurrencyClick() {
    presenter.onCurrencyClick();
  }

  @DebugLog
  @OnTextChanged(value = R.id.send_amount_edittext, callback = OnTextChanged.Callback.TEXT_CHANGED)
  void onSendTextChanged(Editable editable) {
    presenter.onSendTextChanged(editable.toString(), sendEditText.isFocused());
  }

  @DebugLog
  @OnTextChanged(value = R.id.recipient_amount_edittext, callback = OnTextChanged.Callback.TEXT_CHANGED)
  void onRecipientTextChanged(Editable editable) {
    presenter.onRecipientTextChanged(editable.toString(), recipientEditText.isFocused());
  }

  @OnClick(R.id.confirm_button) void onConfirmClick() {
    //presenter.onConfirmClick();
    if (currencyButton.getText().equals(CountryEnum.VI.getCurrency())) {
      ViConfirm viConfirm = new ViConfirm();
      viConfirm.setSendAmount(viConfirmSendAmountTextView.getText().toString());
      viConfirm.setRecipientAmount(viConfirmRecipientAmountTextView.getText().toString());
      viConfirm.setPickupBankName(viConfirmPickupBankNameEditText.getText().toString());
      viConfirm.setPayeeName(viConfirmPayeeNameEditText.getText().toString());
      viConfirm.setPayeePhone(viConfirmPayeePhoneEditText.getText().toString());
      presenter.onViConfirmClick(viConfirm);
    } else if (currencyButton.getText().equals(CountryEnum.ID.getCurrency())) {
      IdConfirm idConfirm = new IdConfirm();
      idConfirm.setSendAmount(idConfirmSendAmountTextView.getText().toString());
      idConfirm.setRecipientAmount(idConfirmRecipientAmountTextView.getText().toString());
      idConfirm.setBankName(idConfirmBankNameEditText.getText().toString());
      idConfirm.setPayeeName(idConfirmPayeeNameEditText.getText().toString());
      idConfirm.setPayeeAccountNumber(idConfirmPayeeAccountNumberEditText.getText().toString());
      idConfirm.setPayeePhone(idConfirmPayeePhoneEditText.getText().toString());
      idConfirm.setPayeeAddress(idConfirmPayeeAddressEditText.getText().toString());
      presenter.onIdConfirmClick(idConfirm);
    }
  }

  @Override public void showEmptyMessage() {
    new AlertDialog.Builder(this).setTitle("!")
        .setMessage("모두 입력하셔야 합니다.")
        .setPositiveButton(android.R.string.ok,
            (dialogInterface, which) -> dialogInterface.dismiss())
        .show();
  }

  @Override public void showConfirmDialog(String text) {
    new AlertDialog.Builder(this).setTitle("송금정보")
        .setMessage(text)
        .setPositiveButton(android.R.string.ok,
            (dialogInterface, which) -> dialogInterface.dismiss());
  }
}