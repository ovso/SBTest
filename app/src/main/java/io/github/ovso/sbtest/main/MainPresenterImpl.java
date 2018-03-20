package io.github.ovso.sbtest.main;

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

  public MainPresenterImpl(MainPresenter.View view, NetworkHelper network) {
    this.view = view;
    this.network = network;
  }

  @Override public void onCreate() {
    network.getNetworkApi()
        .getLanguages()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(languages -> {
          this.languages = languages;
          view.setListener();
        }, throwable -> {
          Timber.d(throwable);
          view.showMessage(R.string.error_server);
        });
  }

  @Override public void onCountryClick() {
  }

  @Override public void onCurrencyClick() {
    final String[] currencys = new String[] { "VND", "IDR" };
    final String[] countries = new String[] { "베트남", "인도네시아" };
    view.showCurrencyDialog(currencys, (dialogInterface, which) -> {
      dialogInterface.dismiss();
      view.showCurrency(currencys[which]);
      view.showCountry(countries[which]);
    });
  }
}
