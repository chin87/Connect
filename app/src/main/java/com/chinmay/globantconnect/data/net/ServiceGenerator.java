package com.chinmay.globantconnect.data.net;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public class ServiceGenerator {
    /*private static IConnectDataService testService;
    public static IConnectDataService getConnectDataService(){
        if(testService==null){
            testService = RetrofitHelper.getRetrofit().create(TownshipService.class);
        }
        return testService;
    }*/
    private static TestService testService;
    public static TestService getConnectDataService(){
        if(testService ==null){
            testService = RetrofitHelper.getRetrofit().create(TestService.class);
        }
        return testService;
    }

}
