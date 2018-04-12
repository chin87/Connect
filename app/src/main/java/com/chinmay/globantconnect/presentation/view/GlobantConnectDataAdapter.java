package com.chinmay.globantconnect.presentation.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinmay.globantconnect.presentation.model.GlobantConnectData;
import com.chinmay.globnantconnect.R;

import java.util.ArrayList;


/**
 * Created by chinmaydeshpande on 30/10/17.
 */
public class GlobantConnectDataAdapter extends RecyclerView.Adapter<GlobantConnectDataAdapter.ConnectDataBindingHolder> {

	private Context mContext;
	private ArrayList<GlobantConnectData> mStoryList;
	private IClick iClick;

	public GlobantConnectDataAdapter(Context context, ArrayList<GlobantConnectData> storyList, IClick iClick) {
		mContext = context;
		mStoryList = storyList;
		this.iClick = iClick;
	}

	@Override
	public ConnectDataBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.globant_connect_item, parent,
				false);
		return new ConnectDataBindingHolder(view);
	}

	@Override
	public void onBindViewHolder(ConnectDataBindingHolder holder, int position) {
		final GlobantConnectData story = mStoryList.get(position);
		holder.getBinding().setVariable(com.chinmay.globnantconnect.BR.story, story);
		holder.getBinding().setVariable(com.chinmay.globnantconnect.BR.callback, this);
		holder.getBinding().executePendingBindings();

	}

	@Override
	public int getItemCount() {
		return mStoryList.size();
	}

	public class ConnectDataBindingHolder extends RecyclerView.ViewHolder {
		private ViewDataBinding binding;
		private GlobantConnectData storyResponse;

		public ConnectDataBindingHolder(View rowView) {
			super(rowView);
			binding = DataBindingUtil.bind(rowView);
			rowView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					itemCLicked(getStoryResponse());
				}
			});
		}

		public ViewDataBinding getBinding() {
			return binding;
		}

		private GlobantConnectData getStoryResponse() {
			return storyResponse;
		}

		private void setStoryResponse(GlobantConnectData storyResponse) {
			this.storyResponse = storyResponse;
		}
	}

	public void update(int position, GlobantConnectData storyResponse) {
		mStoryList.set(position, storyResponse);
		notifyItemChanged(position);
	}

	public void itemCLicked(GlobantConnectData storyResponse) {
		/*Log.i("", "" + storyResponse.getUrl());
		if (iClick != null) {
			iClick.clickedForUrl(storyResponse.getUrl());
		}*/
	}
}
