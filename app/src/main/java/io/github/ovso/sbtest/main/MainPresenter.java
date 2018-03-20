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

  interface View {
    void setListener();

    void showCurrencyDialog(String[] countries, DialogInterface.OnClickListener onClickListener);

    void showMessage(String msg);
    void showMessage(@StringRes int resId);

    void showCurrency(String name);

    void showCountry(String country);
  }
}
