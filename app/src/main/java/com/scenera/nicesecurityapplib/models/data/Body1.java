package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body1 {
    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("AppEndPoint")
    @Expose
    private AppEndPointAppSecurity appEndPoint;
    @SerializedName("NetEndPoint")
    @Expose
    private NetEndPointAppSecurity netEndPoint;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public AppEndPointAppSecurity getAppEndPoint() {
        return appEndPoint;
    }

    public void setAppEndPoint(AppEndPointAppSecurity appEndPoint) {
        this.appEndPoint = appEndPoint;
    }

    public NetEndPointAppSecurity getNetEndPoint() {
        return netEndPoint;
    }

    public void setNetEndPoint(NetEndPointAppSecurity netEndPoint) {
        this.netEndPoint = netEndPoint;
    }

}
