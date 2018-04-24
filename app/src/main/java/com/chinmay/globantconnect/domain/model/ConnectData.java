package com.chinmay.globantconnect.domain.model;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public class ConnectData {

    private String timestamp;
    private String title;
    private String shortDescription;
    private String image;
    private String URL;
    private String PDF;
    private String description;

    public ConnectData() {

    }

    public ConnectData(String timestamp, String title, String shortDescription, String image, String URL, String PDF,
                       String description) {
        this.timestamp = timestamp;
        this.title = title;
        this.shortDescription = shortDescription;
        this.image = image;
        this.URL = URL;
        this.PDF = PDF;
        this.description = description;
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
