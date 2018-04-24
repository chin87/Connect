package com.chinmay.globantconnect.presentation.model;

import com.chinmay.globantconnect.domain.model.ConnectData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public class ConnectDataModelMapper {
    public ConnectDataModelMapper(){

    }
    public GlobantConnectData transform(ConnectData connectData){
        GlobantConnectData globantConnectData = null;
        if(connectData!=null){
            globantConnectData = new GlobantConnectData(connectData.getTimestamp(),
                    connectData.getTitle(),
                    connectData.getShortDescription(),
                    connectData.getImage(),
                    connectData.getURL(),
                    connectData.getPDF(),
                    connectData.getDescription());
        }
        return globantConnectData;
    }

    public List<GlobantConnectData> transformList(List<ConnectData> connectDataList){
        List<GlobantConnectData> globantConnectData = new ArrayList<>();
        for(ConnectData connectData:connectDataList){
            if(connectData!=null){
                globantConnectData.add(transform(connectData));
            }
        }
        return globantConnectData;
    }
}
