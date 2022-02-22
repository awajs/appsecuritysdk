package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceControlObject {
    @SerializedName("DeviceID")
    @Expose
    private String deviceID;
    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("ControlEndPoints")
    @Expose
    private List<ControlEndPoint2> controlEndPoints = null;
    @SerializedName("RevokedJSONTokenIDs")
    @Expose
    private List<Object> revokedJSONTokenIDs = null;
    @SerializedName("AllowedTLSRootCertificates")
    @Expose
    private List<Object> allowedTLSRootCertificates = null;

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<ControlEndPoint2> getControlEndPoints() {
        return controlEndPoints;
    }

    public void setControlEndPoints(List<ControlEndPoint2> controlEndPoints) {
        this.controlEndPoints = controlEndPoints;
    }

    public List<Object> getRevokedJSONTokenIDs() {
        return revokedJSONTokenIDs;
    }

    public void setRevokedJSONTokenIDs(List<Object> revokedJSONTokenIDs) {
        this.revokedJSONTokenIDs = revokedJSONTokenIDs;
    }

    public List<Object> getAllowedTLSRootCertificates() {
        return allowedTLSRootCertificates;
    }

    public void setAllowedTLSRootCertificates(List<Object> allowedTLSRootCertificates) {
        this.allowedTLSRootCertificates = allowedTLSRootCertificates;
    }
}
