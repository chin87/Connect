package com.chinmay.globnantconnect.UI;

import com.chinmay.globantconnect.presentation.presenter.DataCatalogPresenter;
import com.chinmay.globantconnect.presentation.WorldBankDataContract;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by chinmay.deshpande on 13/11/17.
 */
public class DataCatalogPresenterTest {

    DataCatalogPresenter dataCatalogPresenter;
    WorldBankDataContract.View view;

    @Before
    public void init(){
        view = mock(WorldBankDataContract.View.class);
    }

    @Test
    public void testProgress(){
        dataCatalogPresenter.loadDataCatlog();
        verify(view).showProgressBar();
        //verify(view).removeProgressBar();
    }

    @Test//(expected = NullPointerException.class)
    public void testDataResponse(){
        dataCatalogPresenter.loadDataCatlog();
        //verify(view).showProgressBar();
        //doThrow().when(view).removeProgressBar();
        //verify(view,timeout(10000).atLeastOnce()).removeProgressBar();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataResponseX(){
        //doReturn(1)
        dataCatalogPresenter.loadDataCatlog();
        doThrow(IllegalArgumentException.class)
                .when(view).removeProgressBar();
    }
}