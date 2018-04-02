package com.chinmay.globantconnect.UI;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chinmay.globantconnect.POJO.Datacatalog;
import com.chinmay.globantconnect.POJO.GlobantConnectData;
import com.chinmay.globantconnect.WorldBankDataContract;
import com.chinmay.globnantconnect.R;
import com.chinmay.globnantconnect.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IClick, WorldBankDataContract.View{
	private ActivityMainBinding activityMainHackerNewsBinding;
	private DataCatalogPresenter dataCatalogPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dataCatalogPresenter = new DataCatalogPresenter(this);
		activityMainHackerNewsBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		activityMainHackerNewsBinding.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dataCatalogPresenter.loadDataCatlog();
			}
		});

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
	public void showDbCatalog(ArrayList<GlobantConnectData> datacatalogArrayList) {
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
		public UIDbDataRunnable(ArrayList<GlobantConnectData> stories){
			this.stories = stories;
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
