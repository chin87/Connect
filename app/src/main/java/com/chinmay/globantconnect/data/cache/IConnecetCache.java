package com.chinmay.globantconnect.data.cache;

import com.chinmay.globantconnect.data.entity.GlobantConnectEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public interface IConnecetCache {

    boolean isExpired();

    boolean isCached();

    Observable<List<GlobantConnectEntity>> get();

    void put(List<GlobantConnectEntity> globantConnectEntities);
}

