package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 21-Sep-2020
 */
public class ControlEndPoint implements Parcelable
{

    @SerializedName("EndPointType")
    @Expose
    private String endPointType;
    @SerializedName("AppEndPoint")
    @Expose
    private AppEndPointAppControl appEndPointAppControl;
    @SerializedName("NetEndPoint")
    @Expose
    private NetEndPointAppControl netEndPointAppControl;
    public final static Creator<ControlEndPoint> CREATOR = new Creator<ControlEndPoint>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ControlEndPoint createFromParcel(Parcel in) {
            return new ControlEndPoint(in);
        }

        public ControlEndPoint[] newArray(int size) {
            return (new ControlEndPoint[size]);
        }

    }
            ;

    protected ControlEndPoint(Parcel in) {
        this.appEndPointAppControl = ((AppEndPointAppControl) in.readValue((AppEndPointAppControl.class.getClassLoader())));
        this.netEndPointAppControl = ((NetEndPointAppControl) in.readValue((NetEndPointAppControl.class.getClassLoader())));
        this.endPointType = in.readString();
    }

    public ControlEndPoint() {
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

    public String getEndPointType() {
        return endPointType;
    }

    public void setEndPointType(String endPointType) {
        this.endPointType = endPointType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(appEndPointAppControl);
        dest.writeValue(netEndPointAppControl);
        dest.writeString(endPointType);
    }

    public int describeContents() {
        return 0;
    }

}
