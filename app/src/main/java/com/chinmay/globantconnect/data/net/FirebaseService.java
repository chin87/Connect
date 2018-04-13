package com.chinmay.globantconnect.data.net;

import com.chinmay.globantconnect.data.entity.GlobantConnectEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public interface FirebaseService {
    @GET("/bins/pwuef") //https://api.myjson.com/bins/pwuef //items.json
    Observable<List<GlobantConnectEntity>> getConnectData();

    /*private static final String _NODE = "items";

    public static Observable<List<GlobantConnectEntity>> getConnectData(){
        DatabaseReference query = FirebaseDatabase.getInstance().getReference().child(_NODE);
        RxFirebaseDatabase.observeSingleValueEvent(query,
                dataSnapshot -> {
                    List<GlobantConnectEntity> globantConnectData = new ArrayList<>();
                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                        GlobantConnectEntity user = postSnapShot.getValue(GlobantConnectEntity.class);
                        globantConnectData.add(user);
                    }

                     return globantConnectData;//Observable.just(globantConnectData );
                });
        return null;
    }*/
}
