package com.chinmay.globantconnect.presentation.model;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.chinmay.globnantconnect.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by chinmay.deshpande on 26/03/18.
 */

public class GlobantConnectData {

    private String title;
    private String shortDescription;
    private String image;
    private String URL;
    private String PDF;
    private String timestamp;

    public GlobantConnectData(String timestamp, String title, String shortDescription, String image, String URL, String PDF) {
        this.timestamp = timestamp;
        this.title = title;
        this.shortDescription = shortDescription;
        this.image = image;
        this.URL = URL;
        this.PDF = PDF;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getImage() {
        return image;
    }

    public String getURL() {
        return URL;
    }

    public String getPDF() {
        return PDF;
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if(imageUrl == null || imageUrl.isEmpty() ) return;
        String id = imageUrl.substring(imageUrl.lastIndexOf("id"));
        //Create the new image link
        String imageLink="https://drive.google.com/uc?export=download&"+id;
        Picasso.get()
                .load(imageLink)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_not_loaded)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        e.toString();
                    }

                });

    }

    @BindingAdapter("app:visibility")
    public static void setVisibility(View view, String value) {
        view.setVisibility( (value == null || value.isEmpty()) ? View.GONE : View.VISIBLE );
    }
}
