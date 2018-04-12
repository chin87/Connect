package com.chinmay.globantconnect.presentation.presenter;

import com.chinmay.globantconnect.domain.interactor.DefaultObserver;
import com.chinmay.globantconnect.domain.interactor.GetConnectDataList;
import com.chinmay.globantconnect.domain.model.ConnectData;
import com.chinmay.globantconnect.presentation.WorldBankDataContract;
import com.chinmay.globantconnect.presentation.model.ConnectDataModelMapper;
import com.chinmay.globantconnect.presentation.model.GlobantConnectData;

import java.util.List;


/**
 * Created by chinmay.deshpande on 10/11/17.
 */

public class DataCatalogPresenter implements WorldBankDataContract.Presenter {

    private WorldBankDataContract.View dataCatlogView;
    private final GetConnectDataList getConnectDataList;
    private final ConnectDataModelMapper connectDataModelMapper;

    public DataCatalogPresenter(WorldBankDataContract.View view, GetConnectDataList getConnectDataList, ConnectDataModelMapper connectDataModelMapper){
        dataCatlogView = view;
        this.getConnectDataList = getConnectDataList;
        this.connectDataModelMapper = connectDataModelMapper;
        onStart();
    }

    @Override
    public void loadDataCatlog() {
        if(dataCatlogView != null ) {
            dataCatlogView.showProgressBar();
        }
        //WebCommunicator.getAllCatalog();
        //new FirebaseDataRepository().data();
        getDataList();
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
    }

    private void onStop() {

    }

    public void showDataList(List<ConnectData> connectDataList) {
        if (connectDataList == null || connectDataList.size() == 0) {
            if(dataCatlogView != null ) {
                dataCatlogView.removeProgressBar();
                dataCatlogView.showMessage("FAILED TO FETCH NEWS");
            }
        } else {
            parseFirebaseData(connectDataList);
        }
    }

    private void parseFirebaseData(List<ConnectData> connectDataList){

        if(dataCatlogView != null ) {
            dataCatlogView.removeProgressBar();
            final List<GlobantConnectData> globantConnectDataList =
                    this.connectDataModelMapper.transformList(connectDataList);
            dataCatlogView.showDbCatalog(globantConnectDataList);
        }
    }

    private void getDataList() {
        this.getConnectDataList.execute(new ConnectDataListObserver(), null);
    }

    private final class ConnectDataListObserver extends DefaultObserver<List<ConnectData>> {

        @Override public void onComplete() {
            dataCatlogView.removeProgressBar();        }

        @Override public void onError(Throwable e) {
            e.printStackTrace();
            dataCatlogView.removeProgressBar();
            //DataCatalogPresenter.this.showErrorMessage(e.getLocalizedMessage());
        }

        @Override public void onNext(List<ConnectData> connectDataList) {
            DataCatalogPresenter.this.showDataList(connectDataList);
        }
    }
}
