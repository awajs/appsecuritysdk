package com.scenera.nicesecurityapp.models.data;

/**
 * Created by Ekta Bhatt on 21-Jul-2020
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headers_ implements Parcelable
{

    @SerializedName("Accept")
    @Expose
    private String accept;
    @SerializedName("Content-Type")
    @Expose
    private String contentType;
    @SerializedName("content-length")
    @Expose
    private Integer contentLength;
    public final static Creator<Headers_> CREATOR = new Creator<Headers_>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Headers_ createFromParcel(Parcel in) {
            return new Headers_(in);
        }

        public Headers_[] newArray(int size) {
            return (new Headers_[size]);
        }

    }
            ;

    protected Headers_(Parcel in) {
        this.accept = ((String) in.readValue((String.class.getClassLoader())));
        this.contentType = ((String) in.readValue((String.class.getClassLoader())));
        this.contentLength = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Headers_() {
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getContentLength() {
        return contentLength;
    }

    public void setContentLength(Integer contentLength) {
        this.contentLength = contentLength;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accept);
        dest.writeValue(contentType);
        dest.writeValue(contentLength);
    }

    public int describeContents() {
        return 0;
    }

}