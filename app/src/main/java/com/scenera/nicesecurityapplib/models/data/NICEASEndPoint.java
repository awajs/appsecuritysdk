package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NICEASEndPoint {

    @SerializedName("AppEndPoint")
    @Expose
    private AppEndPointAppSecurityForDevice appEndPoint;
    @SerializedName("NetEndPoint")
    @Expose
    private NetEndPointAppSecurity netEndPoint;

    public AppEndPointAppSecurityForDevice getAppEndPoint() {
        return appEndPoint;
    }

    public void setAppEndPoint(AppEndPointAppSecurityForDevice appEndPoint) {
        this.appEndPoint = appEndPoint;
    }

    public NetEndPointAppSecurity getNetEndPoint() {
        return netEndPoint;
    }

    public void setNetEndPoint(NetEndPointAppSecurity netEndPoint) {
        this.netEndPoint = netEndPoint;
    }
}
