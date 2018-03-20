package io.github.ovso.sbtest.main;

/**
 * Created by jaeho on 2018. 3. 20..
 */

public class MainPresenterImpl implements MainPresenter {

  private MainPresenter.View view;
  public MainPresenterImpl(MainPresenter.View view) {
    this.view = view;
  }
  @Override public void onCreate() {

  }
}
