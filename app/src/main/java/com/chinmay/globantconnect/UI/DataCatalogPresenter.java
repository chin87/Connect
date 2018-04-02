package com.chinmay.globantconnect.UI;

import com.chinmay.globantconnect.POJO.FirebaseDBMessageEvent;
import com.chinmay.globantconnect.POJO.GlobantConnectData;
import com.chinmay.globantconnect.WorldBankDataContract;
import com.chinmay.globantconnect.communication.FirebaseCommunicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by chinmay.deshpande on 10/11/17.
 */

public class DataCatalogPresenter implements WorldBankDataContract.Actions {

    private WorldBankDataContract.View dataCatlogView;

    public DataCatalogPresenter(WorldBankDataContract.View view){
        dataCatlogView = view;
        onStart();
    }

    @Override
    public void loadDataCatlog() {
        if(dataCatlogView != null ) {
            dataCatlogView.showProgressBar();
        }
        //WebCommunicator.getAllCatalog();
        FirebaseCommunicator.getInstance().getAllData();
    }

    @Override
    public void viewDestroy() {
        onStop();
    }

    @Override
    public void viewPause() {
        dataCatlogView = null;
    }

    @Override
    public void viewResume(WorldBankDataContract.View view) {
        dataCatlogView = view;
    }

    private void onStart() {
        EventBus.getDefault().register(this);
    }

    private void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(FirebaseDBMessageEvent event) {
        if (event.isSuccess()) {
            parseFirebaseData(event.response);
        } else {
            if(dataCatlogView != null ) {
                dataCatlogView.removeProgressBar();
                dataCatlogView.showMessage("FAILED TO FETCH NEWS");
            }
        }
    }

    private void parseFirebaseData(ArrayList<GlobantConnectData> response){
        if(dataCatlogView != null ) {
            dataCatlogView.removeProgressBar();
            dataCatlogView.showDbCatalog(response);
        }
    }
}
