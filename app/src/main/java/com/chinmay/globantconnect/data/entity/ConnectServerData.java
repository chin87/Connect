package com.chinmay.globantconnect.data.entity;

/**
 * Created by chinmay.deshpande on 13/04/18.
 */

public class ConnectServerData {

    private String timestamp;
    private String title;
    private String shortDescription;
    private String image;
    private String URL;
    private String PDF;

    private String description;

    public ConnectServerData() {

    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }


    public String getDescription() {
        return description;
    }
}
