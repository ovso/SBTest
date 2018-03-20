package io.github.ovso.sbtest.app;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.github.ovso.sbtest.di.DaggerAppComponent;
import io.github.ovso.sbtest.framework.SystemUtils;
import timber.log.Timber;

/**
 * Created by jaeho on 2018. 3. 20
 */

public class MyApplication extends DaggerApplication {

  public static boolean DEBUG = false;

  @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }

  @Override public void onCreate() {
    super.onCreate();
    this.DEBUG = SystemUtils.isDebuggable(this);
    initTimber();
  }

  private void initTimber() {
    if (DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
