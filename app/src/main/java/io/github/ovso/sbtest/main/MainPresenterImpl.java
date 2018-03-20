package io.github.ovso.sbtest.main;

import android.text.TextUtils;
import hugo.weaving.DebugLog;
import io.github.ovso.sbtest.R;
import io.github.ovso.sbtest.main.model.Languages;
import io.github.ovso.sbtest.network.NetworkHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by jaeho on 2018. 3. 20
 */

public class MainPresenterImpl implements MainPresenter {

  private MainPresenter.View view;
  private NetworkHelper network;
  private Languages languages;
  private Country[] countries;
  private Country currentCountry;

  public MainPresenterImpl(MainPresenter.View view, NetworkHelper network, Country[] countries) {
    this.view = view;
    this.network = network;
    this.countries = countries;
  }

  @Override public void onCreate() {
    network.getNetworkApi()
        .getLanguages()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(languages -> {
          this.languages = languages;
          currentCountry = countries[0];
          view.setListener();
        }, throwable -> {
          Timber.d(throwable);
          view.showMessage(R.string.error_server);
        });
  }

  @Override public void onCountryClick() {
  }

  @Override public void onCurrencyClick() {
    final String[] currencies =
        new String[] { countries[0].getCurrency(), countries[1].getCurrency() };

    view.showCurrencyDialog(currencies, (dialogInterface, which) -> {
      currentCountry = countries[which];
      view.showCountry(currentCountry.getCountry());
      view.showCurrency(currentCountry.getCurrency());
      view.requestRecipientFocus();
      dialogInterface.dismiss();
    });
  }

  @DebugLog @Override public void onSendTextChanged(String sendMoneyStr, boolean focused) {
    if (focused) {
      long sendMoney = getInputMoney(sendMoneyStr);
      long money = Math.round(sendMoney * currentCountry.getExchangeRate());
      view.showRecipientMoney(money);
    }
  }

  @DebugLog @Override
  public void onRecipientTextChanged(String recipientMoneyStr, boolean focused) {
    if (focused) {
      long recipientMoney = getInputMoney(recipientMoneyStr);
      long money = Math.round(recipientMoney / currentCountry.getExchangeRate());
      view.showSendMoney(money);
    }
  }

  private long getInputMoney(String inputMoney) {
    if (TextUtils.isEmpty(inputMoney)) {
      return 0;
    } else {
      return Long.valueOf(inputMoney);
    }
  }
}
