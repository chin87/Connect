package com.chinmay.globantconnect.domain.repository;

import com.chinmay.globantconnect.domain.model.ConnectData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public interface IConnectDataRepository {
        Observable<List<ConnectData>> connectData();
}
