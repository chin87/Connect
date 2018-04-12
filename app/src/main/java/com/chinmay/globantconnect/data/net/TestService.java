package com.chinmay.globantconnect.data.net;

import com.chinmay.globantconnect.data.entity.GlobantConnectEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public interface TestService {
    @GET("bins/pwuef") //https://api.myjson.com/bins/pwuef
    Observable<List<GlobantConnectEntity>> getConnectData();
}
