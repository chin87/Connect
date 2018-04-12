package com.chinmay.globantconnect.domain;

import com.chinmay.globantconnect.presentation.model.GlobantConnectData;

import java.util.ArrayList;

/**
 * Created by chinmaydeshpande on 30/10/17.
 */
public class FirebaseDBMessageEvent extends MessageEvent{
	public final ArrayList<GlobantConnectData> response;

	public FirebaseDBMessageEvent(int status,
								  ArrayList<GlobantConnectData> response) {
		this.status = status;
		this.response = response;
	}

	public boolean isSuccess(){
		return status == 0;
	}

}
