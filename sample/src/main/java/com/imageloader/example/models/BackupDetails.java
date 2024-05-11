
package com.imageloader.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BackupDetails {

    @SerializedName("pdfLink")
    @Expose
    private String pdfLink;
    @SerializedName("screenshotURL")
    @Expose
    private String screenshotURL;

    public String getPdfLink() {
        return pdfLink;
    }

    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }

    public String getScreenshotURL() {
        return screenshotURL;
    }

    public void setScreenshotURL(String screenshotURL) {
        this.screenshotURL = screenshotURL;
    }

}
