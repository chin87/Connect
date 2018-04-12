package com.chinmay.globantconnect.domain.interactor;

import com.chinmay.globantconnect.domain.model.ConnectData;
import com.chinmay.globantconnect.domain.repository.IConnectDataRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */


public class GetConnectDataList extends UseCase<List<ConnectData>,Void>{
    private final IConnectDataRepository connectDataRepository;

    public GetConnectDataList(IConnectDataRepository connectDataRepository){
        this.connectDataRepository = connectDataRepository;
    }

    @Override
    Observable<List<ConnectData>> buildUseCaseObservable(Void unused) {
        return connectDataRepository.connectData();
    }
}
