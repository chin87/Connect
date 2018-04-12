package com.chinmay.globantconnect.data.cache;

import com.chinmay.globantconnect.data.entity.GlobantConnectEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public class ConnectCacheImpl implements IConnecetCache {
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    @Override
    public boolean isExpired() {
        Realm realm = Realm.getDefaultInstance();
        if (realm.where(GlobantConnectEntity.class).count() != 0) {
            Date currentTime = new Date(System.currentTimeMillis());
            //SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
            Date lastUpdated = null;
            try {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
                TemporalAccessor accessor = timeFormatter.parse(
                        realm.where(GlobantConnectEntity.class).findFirst().getTimestamp());

                lastUpdated = Date.from(Instant.from(accessor));
                //lastUpdated = ISO8601DATEFORMAT.parse(realm.where(GlobantConnectEntity.class).findFirst().getTimestamp());
                boolean isExpired = currentTime.getTime() - lastUpdated.getTime() > EXPIRATION_TIME;
                if(isExpired){
                    /*realm.beginTransaction();
                    realm.delete(GlobantConnectEntity.class);
                    realm.commitTransaction();
                    realm.close();*/
                }
                return isExpired;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public boolean isCached() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(GlobantConnectEntity.class).findAll() != null && realm.where(GlobantConnectEntity.class).findAll().size() > 0;
    }

    @Override
    public Observable<List<GlobantConnectEntity>> get() {
        List<GlobantConnectEntity> globantConnectEntities = Realm.getDefaultInstance().where(GlobantConnectEntity.class).findAll();
        return Observable.just(globantConnectEntities);
    }

    @Override
    public void put(List<GlobantConnectEntity> globantConnectEntities) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<GlobantConnectEntity> entitiesUpdated = realm.copyToRealmOrUpdate(globantConnectEntities);
        realm.commitTransaction();
        realm.close();
    }
}
