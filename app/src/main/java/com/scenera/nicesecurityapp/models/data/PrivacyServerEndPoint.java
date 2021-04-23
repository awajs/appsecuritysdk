package com.scenera.nicesecurityapp.models.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrivacyServerEndPoint implements Parcelable {

    @SerializedName("AppEndPoint")
    @Expose
    private Object appEndPoint;
    @SerializedName("NetEndPoint")
    @Expose
    private NetEndPointPrivacy netEndPoint;
    public final static Creator<PrivacyServerEndPoint> CREATOR = new Creator<PrivacyServerEndPoint>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PrivacyServerEndPoint createFromParcel(Parcel in) {
            return new PrivacyServerEndPoint(in);
        }

        public PrivacyServerEndPoint[] newArray(int size) {
            return (new PrivacyServerEndPoint[size]);
        }

    }
            ;

    protected PrivacyServerEndPoint(Parcel in) {
        this.appEndPoint = ((Object) in.readValue((Object.class.getClassLoader())));
        this.netEndPoint = ((NetEndPointPrivacy) in.readValue((NetEndPointPrivacy.class.getClassLoader())));
    }

    public PrivacyServerEndPoint() {
    }

    public Object getAppEndPoint() {
        return appEndPoint;
    }

    public void setAppEndPoint(Object appEndPoint) {
        this.appEndPoint = appEndPoint;
    }

    public NetEndPointPrivacy getNetEndPoint() {
        return netEndPoint;
    }

    public void setNetEndPoint(NetEndPointPrivacy netEndPoint) {
        this.netEndPoint = netEndPoint;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(appEndPoint);
        dest.writeValue(netEndPoint);
    }

    public int describeContents() {
        return 0;
    }
  /*  @SerializedName("NetEndPoint")
    @Expose
    private NetEndPointPrivacy netEndPoint;
    public final static Parcelable.Creator<PrivacyServerEndPoint> CREATOR = new Creator<PrivacyServerEndPoint>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PrivacyServerEndPoint createFromParcel(Parcel in) {
            return new PrivacyServerEndPoint(in);
        }

        public PrivacyServerEndPoint[] newArray(int size) {
            return (new PrivacyServerEndPoint[size]);
        }

    }
            ;

    protected PrivacyServerEndPoint(Parcel in) {
        this.netEndPoint = ((NetEndPointPrivacy) in.readValue((NetEndPointPrivacy.class.getClassLoader())));
    }

    public PrivacyServerEndPoint() {
    }

    public NetEndPointPrivacy getNetEndPoint() {
        return netEndPoint;
    }

    public void setNetEndPoint(NetEndPointPrivacy netEndPoint) {
        this.netEndPoint = netEndPoint;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(netEndPoint);
    }

    public int describeContents() {
        return 0;
    }*/
}
