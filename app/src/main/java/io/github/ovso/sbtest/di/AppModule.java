package io.github.ovso.sbtest.di;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import javax.inject.Singleton;

/**
 * Created by jaeho on 2017. 10. 16
 */
@Module public class AppModule {
  @Singleton Context provideContext(Application application) {
    return application;
  }
}
