package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ControlEndPoint2 {

    @SerializedName("AppEndPoint")
    @Expose
    private AppEndPoint appEndPoint;
    @SerializedName("NetEndPoint")
    @Expose
    private NetEndPointAppControl netEndPoint;

    public AppEndPoint getAppEndPoint() {
        return appEndPoint;
    }

    public void setAppEndPoint(AppEndPoint appEndPoint) {
        this.appEndPoint = appEndPoint;
    }

    public NetEndPointAppControl getNetEndPoint() {
        return netEndPoint;
    }

    public void setNetEndPoint(NetEndPointAppControl netEndPoint) {
        this.netEndPoint = netEndPoint;
    }
}
