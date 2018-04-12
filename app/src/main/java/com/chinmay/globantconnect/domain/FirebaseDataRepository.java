package com.chinmay.globantconnect.domain;

import com.chinmay.globantconnect.data.net.FirebaseCommunicator;
import com.chinmay.globantconnect.presentation.model.GlobantConnectData;

import java.util.List;

/**
 * Created by chinmay.deshpande on 10/04/18.
 */

public class FirebaseDataRepository implements FirebaseRepository {

    @Override
    public List<GlobantConnectData> data() {
         FirebaseCommunicator.getInstance().getAllData();
        return null;
    }
}
