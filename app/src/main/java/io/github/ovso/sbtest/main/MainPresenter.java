package io.github.ovso.sbtest.main;

/**
 * Created by jaeho on 2018. 3. 20
 */

public interface MainPresenter {
  void onCreate();
  interface View {
    void setListener();
  }
}
