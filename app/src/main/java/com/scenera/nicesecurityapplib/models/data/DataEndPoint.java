package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 4/6/2020.
 */
public class DataEndPoint implements Parcelable
{

    @SerializedName("AppEndPoint")
    @Expose
    private AppEndPointAppControl appEndPointAppControl;
    @SerializedName("NetEndPoint")
    @Expose
    private NetEndPointAppControl netEndPointAppControl;
    public final static Creator<DataEndPoint> CREATOR = new Creator<DataEndPoint>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DataEndPoint createFromParcel(Parcel in) {
            return new DataEndPoint(in);
        }

        public DataEndPoint[] newArray(int size) {
            return (new DataEndPoint[size]);
        }

    }
            ;

    protected DataEndPoint(Parcel in) {
        this.appEndPointAppControl = ((AppEndPointAppControl) in.readValue((AppEndPointAppControl.class.getClassLoader())));
        this.netEndPointAppControl = ((NetEndPointAppControl) in.readValue((NetEndPointAppControl.class.getClassLoader())));
    }

    public DataEndPoint() {
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
