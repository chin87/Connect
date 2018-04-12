package com.chinmay.globantconnect.data.repository.datasource;

import com.chinmay.globantconnect.data.cache.IConnecetCache;
import com.chinmay.globantconnect.data.entity.GlobantConnectEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public class ConnectLocalDataStore implements IConnectDataStore {
    private final IConnecetCache connecetCache;

    public ConnectLocalDataStore(IConnecetCache connecetCache) {
        this.connecetCache = connecetCache;
    }

    //Return a list of connect data from DB
    @Override
    public Observable<List<GlobantConnectEntity>> items() {
        return connecetCache.get();
    }
}
