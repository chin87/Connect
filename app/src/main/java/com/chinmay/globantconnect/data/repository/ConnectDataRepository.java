package com.chinmay.globantconnect.data.repository;

import com.chinmay.globantconnect.data.entity.ConnectDataMapper;
import com.chinmay.globantconnect.data.entity.GlobantConnectEntity;
import com.chinmay.globantconnect.data.repository.datasource.ConnectDataStoreFactory;
import com.chinmay.globantconnect.domain.model.ConnectData;
import com.chinmay.globantconnect.domain.repository.IConnectDataRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public class ConnectDataRepository implements IConnectDataRepository {
        private final ConnectDataStoreFactory connectDataStoreFactory;
        private final ConnectDataMapper connectDataMapper;
        public ConnectDataRepository(ConnectDataStoreFactory connectDataStoreFactory,ConnectDataMapper connectDataMapper){
            this.connectDataStoreFactory = connectDataStoreFactory;
            this.connectDataMapper = connectDataMapper;
        }

        @Override
        public Observable<List<ConnectData>> connectData() {
            return connectDataStoreFactory.create()
                    .items()
                    .map(new Function<List<GlobantConnectEntity>, List<ConnectData>>() {
                @Override
                public List<ConnectData> apply(List<GlobantConnectEntity> globantConnectEntities) throws Exception {
                    return connectDataMapper.transformList(globantConnectEntities);                }
            });
        }
}
