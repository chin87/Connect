package com.chinmay.globantconnect.data.repository.datasource;

import com.chinmay.globantconnect.data.cache.IConnecetCache;
import com.chinmay.globantconnect.data.entity.ConnectServerData;
import com.chinmay.globantconnect.data.entity.GlobantConnectEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public class ConnectCloudDataStore implements IConnectDataStore {
    private final IConnecetCache connecetCache;
    private static final String _NODE = "items";

    public ConnectCloudDataStore(IConnecetCache connecetCache) {
        this.connecetCache = connecetCache;
    }

    @Override
    public Observable<List<GlobantConnectEntity>> items() {

        /*return ServiceGenerator.getFirebaseService()
                .getConnectData()
                .doOnNext(new Consumer<List<GlobantConnectEntity>>() {
            @Override
            public void accept(final List<GlobantConnectEntity> globantConnectEntities) throws Exception {
                connecetCache.put(globantConnectEntities);
            }
        });*/

       return Observable.create(new ObservableOnSubscribe<List<GlobantConnectEntity>>() {

            @Override
            public void subscribe(ObservableEmitter<List<GlobantConnectEntity>> emitter) throws Exception {
                FirebaseDatabase.getInstance().getReference().child(_NODE).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            List<GlobantConnectEntity> globantConnectData = new ArrayList<>();
                            for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                                ConnectServerData connectServerData = postSnapShot.getValue(ConnectServerData.class);
                                GlobantConnectEntity globantConnectEntity = new GlobantConnectEntity(connectServerData);
                                globantConnectData.add(globantConnectEntity);
                            }
                            emitter.onNext(globantConnectData);
                            emitter.onComplete();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //EventBus.getDefault().post(new FirebaseDBMessageEvent(MessageEvent.FAIL, null));
                        emitter.onError( new Throwable(databaseError.getMessage()) );
                    }
                });
            }
        });
    }
}
