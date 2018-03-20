package io.github.ovso.sbtest.network;

import io.github.ovso.sbtest.main.model.Languages;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by jaeho on 2017. 12. 28
 */

public interface NetworkApi {

  public final static String BASE_URL = "https://staging.sentbe.com:3000";

  @GET("/assignment/languages") Single<Languages> getLanguages();
}