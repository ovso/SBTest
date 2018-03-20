package io.github.ovso.sbtest.main;

import android.os.Bundle;
import io.github.ovso.sbtest.R;
import io.github.ovso.sbtest.framework.customview.BaseActivity;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainPresenter.View {

  @Inject
  MainPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate();
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override public void setListener() {

  }
}
