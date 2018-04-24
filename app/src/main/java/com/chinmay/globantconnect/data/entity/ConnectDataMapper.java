package com.chinmay.globantconnect.data.entity;

import com.chinmay.globantconnect.domain.model.ConnectData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public class ConnectDataMapper {
    public ConnectDataMapper() {

    }

    public ConnectData transform(GlobantConnectEntity globantConnectEntity) {
        ConnectData connectData = null;
        if (globantConnectEntity != null) {
            connectData = new ConnectData(globantConnectEntity.getTimestamp(),
                    globantConnectEntity.getTitle(),
                    globantConnectEntity.getShortDescription(),
                    globantConnectEntity.getImage(),
                    globantConnectEntity.getURL(),
                    globantConnectEntity.getPDF(),
                    globantConnectEntity.getDescription());
        }
        return connectData;
    }

    public List<ConnectData> transformList(List<GlobantConnectEntity> globantConnectEntities) {
        List<ConnectData> connectData = new ArrayList<>();
        for (GlobantConnectEntity globantConnectEntity : globantConnectEntities) {
            if (globantConnectEntity != null) {
                connectData.add(transform(globantConnectEntity));
            }
        }
        return connectData;
    }
}