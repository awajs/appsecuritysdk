package com.scenera.nicesecurityapp.models.data;

/**
 * Created by Ekta Bhatt on 21-Jul-2020
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request implements Parcelable
{

    @SerializedName("uri")
    @Expose
    private Uri uri;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("headers")
    @Expose
    private Headers_ headers;
    public final static Creator<Request> CREATOR = new Creator<Request>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        public Request[] newArray(int size) {
            return (new Request[size]);
        }

    }
            ;

    protected Request(Parcel in) {
        this.uri = ((Uri) in.readValue((Uri.class.getClassLoader())));
        this.method = ((String) in.readValue((String.class.getClassLoader())));
        this.headers = ((Headers_) in.readValue((Headers_.class.getClassLoader())));
    }

    public Request() {
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Headers_ getHeaders() {
        return headers;
    }

    public void setHeaders(Headers_ headers) {
        this.headers = headers;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(uri);
        dest.writeValue(method);
        dest.writeValue(headers);
    }

    public int describeContents() {
        return 0;
    }

}