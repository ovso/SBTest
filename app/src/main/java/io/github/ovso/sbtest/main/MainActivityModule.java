package io.github.ovso.sbtest.main;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.sbtest.network.NetworkApi;
import io.github.ovso.sbtest.network.NetworkHelper;

/**
 * Created by jaeho on 2018. 3. 20
 */

@Module public abstract class MainActivityModule {
  @Provides
  public static MainPresenter provideMainPresenter(MainActivity activity, NetworkHelper network,
      CountryEnum[] countries) {
    return new MainPresenterImpl(activity, network, countries);
  }

  @Provides public static NetworkHelper provideNetworkHelper() {
    return new NetworkHelper(NetworkApi.BASE_URL);
  }

  @Provides public static CountryEnum[] provideCountries() {
    return new CountryEnum[] { CountryEnum.VI, CountryEnum.ID };
  }
}
