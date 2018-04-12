package com.chinmay.globantconnect.data.repository.datasource;

import com.chinmay.globantconnect.data.entity.GlobantConnectEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chinmay.deshpande on 10/04/18.
 */

public interface IConnectDataStore {

    Observable<List<GlobantConnectEntity>> items();

}
