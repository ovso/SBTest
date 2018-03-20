package io.github.ovso.sbtest.framework;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by jaeho on 2018. 3. 20..
 */

public class SystemUtils {
  public static boolean isDebuggable(Context context) {
    boolean debuggable = false;

    PackageManager pm = context.getPackageManager();
    try {
      ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
      debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
    } catch (PackageManager.NameNotFoundException e) {
      /* debuggable variable will remain false */
    }

    return debuggable;
  }

}
