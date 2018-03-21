package io.github.ovso.sbtest.main;

import android.content.DialogInterface;
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
          view.showViLayout();
          view.setListener();

          // vi
          view.showViPickupBankName(languages.getVi().getPickupBank());
          view.showViConfirmAccountNumber(languages.getVi().getAcctNo());
          view.showViPayeeName(
              languages.getVi().getFirstName() + " " + languages.getVi().getLastName());

          //id
          view.showIdBankName(languages.getId().getBank());
          view.showIdConfirmAccountNumber(languages.getId().getAcctNo());
          view.showIdPayeeName(
              languages.getId().getFirstName() + " " + languages.getId().getLastName());

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

    view.showCurrencyDialog(currencies, (DialogInterface dialogInterface, int which) -> {
      currentCountry = countries[which];
      view.showCountry(currentCountry.getCountry());
      view.showCurrency(currentCountry.getCurrency());

      switch (currentCountry) {
        case VI:
          view.showViLayout();
          break;
        case ID:
          view.showIdLayout();
          break;
      }
      view.requestRecipientFocus();
      dialogInterface.dismiss();
    });
  }

  @DebugLog @Override public void onSendTextChanged(String sendMoneyStr, boolean focused) {
    if (focused) {
      long sendMoney = getInputMoney(sendMoneyStr);
      long money = Math.round(sendMoney * currentCountry.getExchangeRate());
      view.showRecipientMoney(money);

      switch (currentCountry) {
        case VI:
          view.showViConfirmSendAmount(sendMoneyStr);
          view.showViConfirmRecipientAmount(String.valueOf(money));
          break;
        case ID:
          view.showIdConfirmSendAmount(sendMoneyStr);
          view.showIdConfirmRecipientAmount(String.valueOf(money));
          break;
      }
    }
  }

  @DebugLog @Override
  public void onRecipientTextChanged(String recipientMoneyStr, boolean focused) {
    if (focused) {
      long recipientMoney = getInputMoney(recipientMoneyStr);
      long money = Math.round(recipientMoney / currentCountry.getExchangeRate());
      view.showSendMoney(money);

      switch (currentCountry) {
        case VI:
          view.showViConfirmSendAmount(String.valueOf(money));
          view.showViConfirmRecipientAmount(String.valueOf(recipientMoneyStr));
          break;
        case ID:
          view.showIdConfirmSendAmount(String.valueOf(money));
          view.showIdConfirmRecipientAmount(String.valueOf(recipientMoneyStr));
          break;
      }
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
