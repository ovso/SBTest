package io.github.ovso.sbtest.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.github.ovso.sbtest.main.MainActivity;
import io.github.ovso.sbtest.main.MainActivityModule;

/**
 * Created by jaeho on 2017. 10. 16
 */

@Module public abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = { MainActivityModule.class })
  abstract MainActivity bindMainActivity();
}