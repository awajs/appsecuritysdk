package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationEndPoint implements Parcelable{
    @SerializedName("AppEndPoint")
    @Expose
    private AppEndPointAppControl appEndPointAppControl;
    @SerializedName("NetEndPoint")
    @Expose
    private NetEndPointAppControl netEndPointAppControl;
    public final static Creator<NotificationEndPoint> CREATOR = new Creator<NotificationEndPoint>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NotificationEndPoint createFromParcel(Parcel in) {
            return new NotificationEndPoint(in);
        }

        public NotificationEndPoint[] newArray(int size) {
            return (new NotificationEndPoint[size]);
        }

    }
            ;

    protected NotificationEndPoint(Parcel in) {
        this.appEndPointAppControl = ((AppEndPointAppControl) in.readValue((AppEndPointAppControl.class.getClassLoader())));
        this.netEndPointAppControl = ((NetEndPointAppControl) in.readValue((NetEndPointAppControl.class.getClassLoader())));
    }

    public NotificationEndPoint() {
    }

    public AppEndPointAppControl getAppEndPointAppControl() {
        return appEndPointAppControl;
    }

    public void setAppEndPointAppControl(AppEndPointAppControl appEndPointAppControl) {
        this.appEndPointAppControl = appEndPointAppControl;
    }

    public NetEndPointAppControl getNetEndPointAppControl() {
        return netEndPointAppControl;
    }

    public void setNetEndPointAppControl(NetEndPointAppControl netEndPointAppControl) {
        this.netEndPointAppControl = netEndPointAppControl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(appEndPointAppControl);
        dest.writeValue(netEndPointAppControl);
    }

    public int describeContents() {
        return 0;
    }
}
