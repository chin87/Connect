package com.chinmay.globantconnect.presentation.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chinmay.globantconnect.data.cache.ConnectCacheImpl;
import com.chinmay.globantconnect.data.entity.ConnectDataMapper;
import com.chinmay.globantconnect.data.repository.ConnectDataRepository;
import com.chinmay.globantconnect.data.repository.datasource.ConnectDataStoreFactory;
import com.chinmay.globantconnect.domain.interactor.GetConnectDataList;
import com.chinmay.globantconnect.presentation.WorldBankDataContract;
import com.chinmay.globantconnect.presentation.model.ConnectDataModelMapper;
import com.chinmay.globantconnect.presentation.model.GlobantConnectData;
import com.chinmay.globantconnect.presentation.presenter.DataCatalogPresenter;
import com.chinmay.globnantconnect.R;
import com.chinmay.globnantconnect.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IClick, WorldBankDataContract.View {
	private ActivityMainBinding activityMainHackerNewsBinding;
	private DataCatalogPresenter dataCatalogPresenter;

	private ConnectDataModelMapper connectDataModelMapper;
	private ConnectDataMapper connectDataMapper;
	private ConnectCacheImpl connectCacheImpl;
	private ConnectDataStoreFactory connectDataStoreFactory;
	private ConnectDataRepository connectDataRepository;
	private GetConnectDataList connectDataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		connectDataModelMapper = new ConnectDataModelMapper();
		connectDataMapper = new ConnectDataMapper();
		connectCacheImpl = new ConnectCacheImpl();
		connectDataStoreFactory = new ConnectDataStoreFactory(connectCacheImpl);
		connectDataRepository = new ConnectDataRepository(connectDataStoreFactory, connectDataMapper);
		connectDataList = new GetConnectDataList(connectDataRepository);

		dataCatalogPresenter = new DataCatalogPresenter(this, connectDataList, connectDataModelMapper);
		activityMainHackerNewsBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		activityMainHackerNewsBinding.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dataCatalogPresenter.loadDataCatlog();
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		if( dataCatalogPresenter != null ){
			dataCatalogPresenter.loadDataCatlog();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		dataCatalogPresenter.viewPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		dataCatalogPresenter.viewResume(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dataCatalogPresenter.viewDestroy();
	}

	@Override
	public void clickedForUrl(String url) {
		Intent intent = new Intent(getBaseContext(), WebViewActivity.class);
		intent.putExtra(WebViewActivity.KEY_URL, url);
		startActivity(intent);
	}

	@Override
	public void showMessage(String message) {
		runOnUiThread(new UIMessageRunnable(message));
	}

	@Override
	public void showProgressBar() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				activityMainHackerNewsBinding.pbData.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	public void removeProgressBar() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				activityMainHackerNewsBinding.pbData.setVisibility(View.GONE);
			}
		});
	}

	@Override
	public void showDbCatalog(List<GlobantConnectData> datacatalogArrayList) {
		runOnUiThread(new UIDbDataRunnable(datacatalogArrayList));
	}

	private class UIMessageRunnable implements Runnable{
		private String message;
		public UIMessageRunnable(String message){
			this.message = message;
		}

		@Override
		public void run() {
			Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
		}
	}

	private class UIDbDataRunnable implements Runnable{
		private ArrayList<GlobantConnectData> stories;
		public UIDbDataRunnable(List<GlobantConnectData> stories){
			ArrayList<GlobantConnectData> globantConnectDataArrayList = new ArrayList<>(stories.size());
			globantConnectDataArrayList.addAll(stories);
			this.stories = globantConnectDataArrayList;
		}

		@Override
		public void run() {
			setGlobantData(stories);
		}
	}

	private void setGlobantData( ArrayList<GlobantConnectData> stories ){
		GlobantConnectDataAdapter newsAdapter = new GlobantConnectDataAdapter(this, stories, this);
		activityMainHackerNewsBinding.button.setVisibility(View.GONE);
		activityMainHackerNewsBinding.rvNewsList.setVisibility(View.VISIBLE);
		LinearLayoutManager layoutManager
				= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		activityMainHackerNewsBinding.rvNewsList.setLayoutManager(layoutManager);
		activityMainHackerNewsBinding.rvNewsList.setAdapter(newsAdapter);
	}
}
