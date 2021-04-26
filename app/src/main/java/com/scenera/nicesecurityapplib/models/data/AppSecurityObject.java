package com.scenera.nicesecurityapplib.models.data;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 16,March,2020
 */

public class AppSecurityObject implements Parcelable
{

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("body")
    @Expose
    private Body body;
    @SerializedName("headers")
    @Expose
    private Headers headers;
    @SerializedName("request")
    @Expose
    private Request request;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Creator<AppSecurityObject> CREATOR = new Creator<AppSecurityObject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AppSecurityObject createFromParcel(Parcel in) {
            return new AppSecurityObject(in);
        }

        public AppSecurityObject[] newArray(int size) {
            return (new AppSecurityObject[size]);
        }

    }
            ;

    protected AppSecurityObject(Parcel in) {
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.body = ((Body) in.readValue((Body.class.getClassLoader())));
        this.headers = ((Headers) in.readValue((Headers.class.getClassLoader())));
        this.request = ((Request) in.readValue((Request.class.getClassLoader())));
        this.success = ((String) in.readValue((String.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AppSecurityObject() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(statusCode);
        dest.writeValue(body);
        dest.writeValue(headers);
        dest.writeValue(request);
        dest.writeValue(success);
        dest.writeValue(error);
    }

    public int describeContents() {
        return 0;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "AppSecurityObject{" +
                "statusCode=" + statusCode +
                ", body=" + body +
                ", headers=" + headers +
                ", request=" + request +
                ", success='" + success + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}