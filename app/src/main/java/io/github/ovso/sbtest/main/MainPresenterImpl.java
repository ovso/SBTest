package io.github.ovso.sbtest.main;

import android.content.DialogInterface;
import android.text.TextUtils;
import hugo.weaving.DebugLog;
import io.github.ovso.sbtest.R;
import io.github.ovso.sbtest.main.model.Country;
import io.github.ovso.sbtest.main.model.IdConfirm;
import io.github.ovso.sbtest.main.model.Languages;
import io.github.ovso.sbtest.main.model.ViConfirm;
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
  private CountryEnum[] countries;
  private CountryEnum currentCountry;

  public MainPresenterImpl(MainPresenter.View view, NetworkHelper network,
      CountryEnum[] countries) {
    this.view = view;
    this.network = network;
    this.countries = countries;
  }

  @Override public void onCreate() {
    view.showLoading();
    view.hideRootView();
    network.getNetworkApi()
        .getLanguages()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(languages -> {
          this.languages = languages;
          currentCountry = countries[0];
          view.showViLayout();
          view.setListener();

          view.showRootView();
          showViLable(languages.getVi());
          showIdLable(languages.getId());
          view.hideLoading();
        }, throwable -> {
          Timber.d(throwable);
          view.showMessage(R.string.error_server);
          view.hideLoading();
        });
  }

  private void showViLable(Country vi) {
    view.showViConfirmSendAmountLable(vi.getSendAmount());
    view.showViConfirmRecipientAmountLable(vi.getReceiveAmount());
    view.showViConfirmPickupBankNameLable(vi.getPickupBank());
    view.showViConfirmPayeeNameLabel(vi.getFirstName() + " " + vi.getLastName());
    view.showViConfirmPayeePhoneLable(vi.getMobile());
  }

  private void showIdLable(Country id) {
    view.showIdConfirmSendAmountLable(id.getSendAmount());
    view.showIdConfirmRecipientAmountLable(id.getReceiveAmount());
    view.showIdConfirmBankNameLable(id.getBank());
    view.showIdConfirmPayeeNameLabel(id.getFirstName() + " " + id.getLastName());
    view.showIdConfirmAccountNumberLable(id.getAcctNo());
    view.showIdConfirmPayeePhoneLable(id.getMobile());
    view.showIdConfirmPayeeAddressLabel(id.getAddress());
  }

  @Override public void onCountryClick() {
  }

  @Override public void onCurrencyClick() {
    final String[] currencies =
        new String[] { countries[0].getCurrency(), countries[1].getCurrency() };
    int checkedItem = currentCountry.getType();
    view.showCurrencyDialog(currencies, checkedItem, (DialogInterface dialogInterface, int which) -> {
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
      //view.requestRecipientFocus();
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

  @Override public void onViConfirmClick(ViConfirm viConfirm) {
    if (!ViConfirm.isEmptyValue(viConfirm)) {
      StringBuilder builder = new StringBuilder();
      Country c = languages.getVi();
      builder.append(c.getSendAmount()).append(":").append(viConfirm.getSendAmount()).append(" KRW").append("\n\n");
      builder.append(c.getReceiveAmount())
          .append(":")
          .append(viConfirm.getRecipientAmount()).append(" ").append(CountryEnum.VI.getCurrency())
          .append("\n\n");
      builder.append(c.getPickupBank())
          .append(":")
          .append(viConfirm.getPickupBankName())
          .append("\n\n");
      builder.append(c.getFirstName())
          .append(" ")
          .append(c.getLastName())
          .append(":")
          .append(viConfirm.getPayeeName())
          .append("\n\n");
      builder.append(c.getMobile()).append(":").append(viConfirm.getPayeePhone());
      view.showConfirmDialog(CountryEnum.VI.getCountry(), builder.toString());
    } else {
      view.showEmptyMessage();
    }
  }

  @Override public void onIdConfirmClick(IdConfirm idConfirm) {
    if (!IdConfirm.isEmptyValue(idConfirm)) {
      Country id = languages.getId();
      StringBuilder builder = new StringBuilder();
      builder.append(id.getSendAmount()).append(":").append(idConfirm.getSendAmount()).append(" KRW").append("\n\n");
      builder.append(id.getReceiveAmount())
          .append(":")
          .append(idConfirm.getRecipientAmount()).append(" ").append(CountryEnum.ID.getCurrency())
          .append("\n\n");
      builder.append(id.getPickupBank()).append(":").append(idConfirm.getBankName()).append("\n\n");
      builder.append(id.getFirstName())
          .append(" ")
          .append(id.getLastName())
          .append(":")
          .append(idConfirm.getPayeeName())
          .append("\n\n");
      builder.append(id.getAcctNo())
          .append(":")
          .append(idConfirm.getPayeeAccountNumber())
          .append("\n\n");
      builder.append(id.getMobile()).append(":").append(idConfirm.getPayeePhone()).append("\n\n");
      builder.append(id.getAddress()).append(":").append(idConfirm.getPayeeAddress());
      view.showConfirmDialog(CountryEnum.ID.getCountry(), builder.toString());
    } else {
      view.showEmptyMessage();
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
