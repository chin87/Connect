package com.chinmay.globantconnect.data.net;

import com.chinmay.globantconnect.data.entity.GlobantConnectEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public interface IConnectDataService {
    Observable<List<GlobantConnectEntity>> getConnectData();
}
