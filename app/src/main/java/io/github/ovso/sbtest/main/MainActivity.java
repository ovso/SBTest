package io.github.ovso.sbtest.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import io.github.ovso.sbtest.R;
import io.github.ovso.sbtest.framework.customview.BaseActivity;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainPresenter.View {
  @Inject MainPresenter presenter;
  @BindView(R.id.country_textview) TextView countryTextView;
  @BindView(R.id.currency_textview) TextView currencyTextView;
  @BindView(R.id.send_amount_edittext) EditText sendEditText;
  @BindView(R.id.recipient_amount_edittext) EditText recipientEditText;

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
  public void showCurrencyDialog(String[] names, DialogInterface.OnClickListener onClickListener) {
    new AlertDialog.Builder(this).setTitle("국가 설정")
        .setSingleChoiceItems(names, 0, onClickListener)
        .show();
  }

  @Override public void showMessage(String msg) {
    new AlertDialog.Builder(this).setTitle("!!")
        .setMessage(msg)
        .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
          dialogInterface.dismiss();
          fileList();
        })
        .show();
  }

  @Override public void showMessage(@StringRes int resId) {
    new AlertDialog.Builder(this).setTitle("!!")
        .setMessage(resId)
        .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
          dialogInterface.dismiss();
          fileList();
        })
        .show();
  }

  @Override public void showCurrency(String currency) {
    currencyTextView.setText(currency);
  }

  @Override public void showCountry(String country) {
    countryTextView.setText(country);
  }

  @OnClick(R.id.country_textview) void onCountryClick() {
    presenter.onCountryClick();
  }

  @OnClick(R.id.currency_textview) void onCurrencyClick() {
    presenter.onCurrencyClick();
  }
}
