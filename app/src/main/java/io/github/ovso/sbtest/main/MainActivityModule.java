package io.github.ovso.sbtest.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaeho on 2018. 3. 20
 */

@Module public abstract class MainActivityModule {
  @Provides public static MainPresenter provideMainPresenter(MainActivity activity) {
    return new MainPresenterImpl(activity);
  }
}
