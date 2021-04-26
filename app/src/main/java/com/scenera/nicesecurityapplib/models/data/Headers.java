package com.scenera.nicesecurityapplib.models.data;

/**
 * Created by Ekta Bhatt on 21-Jul-2020
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headers implements Parcelable {

    @SerializedName("content-length")
    @Expose
    private String contentLength;
    @SerializedName("content-type")
    @Expose
    private String contentType;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("connection")
    @Expose
    private String connection;
    public final static Creator<Headers> CREATOR = new Creator<Headers>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Headers createFromParcel(Parcel in) {
            return new Headers(in);
        }

        public Headers[] newArray(int size) {
            return (new Headers[size]);
        }

    };

    protected Headers(Parcel in) {
        this.contentLength = ((String) in.readValue((String.class.getClassLoader())));
        this.contentType = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.connection = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Headers() {
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(contentLength);
        dest.writeValue(contentType);
        dest.writeValue(date);
        dest.writeValue(connection);
    }

    public int describeContents() {
        return 0;
    }
}