package com.chinmay.globantconnect.data.net;

import com.chinmay.globantconnect.data.entity.ConnectServerData;
import com.chinmay.globantconnect.data.entity.GlobantConnectEntity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
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

public class FirebaseService {

    private static final String _NODE = "items";

    /*@GET("/bins/pwuef") //https://api.myjson.com/bins/pwuef //items.json
    Observable<List<GlobantConnectEntity>> getConnectData();*/

    public Observable<List<GlobantConnectEntity>> getConnectData(){
        return Observable.create(new ObservableOnSubscribe<List<GlobantConnectEntity>>() {

            @Override
            public void subscribe(ObservableEmitter<List<GlobantConnectEntity>> emitter) throws Exception {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child(_NODE).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        updateDataSnapshot(dataSnapshot, emitter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //EventBus.getDefault().post(new FirebaseDBMessageEvent(MessageEvent.FAIL, null));
                        emitter.onError( new Throwable(databaseError.getMessage()) );
                    }
                });

                databaseReference.addChildEventListener(new ChildEventListener() {

                    @Override public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        updateDataSnapshot(dataSnapshot, emitter);
                    }

                    @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        updateDataSnapshot(dataSnapshot, emitter);
                    }

                    @Override public void onChildRemoved(DataSnapshot dataSnapshot) {
                        updateDataSnapshot(dataSnapshot, emitter);
                    }

                    @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        updateDataSnapshot(dataSnapshot, emitter);
                    }

                    @Override public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void updateDataSnapshot(DataSnapshot dataSnapshot, ObservableEmitter<List<GlobantConnectEntity>> emitter){
        if (dataSnapshot.exists()) {
            List<GlobantConnectEntity> globantConnectData = new ArrayList<>();
            for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                ConnectServerData connectServerData = postSnapShot.getValue(ConnectServerData.class);
                GlobantConnectEntity globantConnectEntity = new GlobantConnectEntity(connectServerData);
                globantConnectData.add(globantConnectEntity);
            }
            emitter.onNext(globantConnectData);
            //emitter.onComplete();
        }
    }
}
