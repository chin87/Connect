package com.chinmay.globantconnect.data.repository.datasource;

import com.chinmay.globantconnect.data.cache.IConnecetCache;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public class ConnectDataStoreFactory {
        private final IConnecetCache connecetCache;

        public ConnectDataStoreFactory(IConnecetCache connecetCache) {
            this.connecetCache = connecetCache;
        }

        public IConnectDataStore create() {
            if (!connecetCache.isExpired() && connecetCache.isCached()) {
                return new ConnectLocalDataStore(connecetCache);
            } else {
                return new ConnectCloudDataStore(connecetCache);
            }
        }
    }
