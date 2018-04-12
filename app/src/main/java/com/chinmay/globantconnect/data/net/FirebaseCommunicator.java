package com.chinmay.globantconnect.data.net;

import com.chinmay.globantconnect.presentation.model.GlobantConnectData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by chinmay.deshpande on 26/03/18.
 */

public class FirebaseCommunicator {
    private static FirebaseCommunicator mFirebaseCommunicator;
    private static final String _NODE = "items";
    private DatabaseReference databaseReference;


    private FirebaseCommunicator() {

        //database reference pointing to root of database
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //accessing the firebase storage
        //storage = FirebaseStorage.getInstance();
        //creates a storage reference
        //storageRef = storage.getReference();
    }

    public static FirebaseCommunicator getInstance() {
        if (mFirebaseCommunicator == null) {
            mFirebaseCommunicator = new FirebaseCommunicator();
        }

        return mFirebaseCommunicator;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return mFirebaseCommunicator;
    }

    public void getAllData() {
        databaseReference.child(_NODE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<GlobantConnectData> globantConnectData = new ArrayList<>();
                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                        GlobantConnectData user = postSnapShot.getValue(GlobantConnectData.class);
                        globantConnectData.add(user);
                    }
                    //EventBus.getDefault().post(new FirebaseDBMessageEvent(MessageEvent.SUCCESS, globantConnectData));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //EventBus.getDefault().post(new FirebaseDBMessageEvent(MessageEvent.FAIL, null));
            }
        });
    }
}
