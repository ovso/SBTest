package io.github.ovso.sbtest.framework.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by jaeho on 2017. 10. 19
 */

public abstract class BaseActivity extends DaggerAppCompatActivity {
  private Unbinder unbinder;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());
    unbinder = ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  protected abstract int getLayoutResId();

}
