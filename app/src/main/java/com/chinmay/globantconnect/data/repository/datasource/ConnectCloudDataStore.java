package com.chinmay.globantconnect.data.repository.datasource;

import com.chinmay.globantconnect.data.cache.IConnecetCache;
import com.chinmay.globantconnect.data.entity.GlobantConnectEntity;
import com.chinmay.globantconnect.data.net.ServiceGenerator;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public class ConnectCloudDataStore implements IConnectDataStore {
    private final IConnecetCache connecetCache;

    public ConnectCloudDataStore(IConnecetCache connecetCache) {
        this.connecetCache = connecetCache;
    }

    @Override
    public Observable<List<GlobantConnectEntity>> items() {

        return ServiceGenerator.getConnectDataService().getConnectData().doOnNext(new Consumer<List<GlobantConnectEntity>>() {
            @Override
            public void accept(final List<GlobantConnectEntity> globantConnectEntities) throws Exception {
                connecetCache.put(globantConnectEntities);
            }
        });

    }
}
