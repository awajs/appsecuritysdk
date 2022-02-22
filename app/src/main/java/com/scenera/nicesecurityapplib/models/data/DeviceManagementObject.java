package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceManagementObject {

    @SerializedName("NICEAS")
    @Expose
    private Niceas niceas;
    @SerializedName("FirmwareSourceCertificates")
    @Expose
    private List<String> firmwareSourceCertificates = null;
    @SerializedName("AllowedTLSRootCertificates")
    @Expose
    private List<String> allowedTLSRootCertificates = null;
    @SerializedName("DeviceID")
    @Expose
    private String deviceID;
    @SerializedName("ExpiryDateTime")
    @Expose
    private String expiryDateTime;
    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("FirmwareSourceID")
    @Expose
    private String firmwareSourceID;
    @SerializedName("DeviceCertificate")
    @Expose
    private List<String> deviceCertificate = null;

    public Niceas getNiceas() {
        return niceas;
    }

    public void setNiceas(Niceas niceas) {
        this.niceas = niceas;
    }

    public List<String> getFirmwareSourceCertificates() {
        return firmwareSourceCertificates;
    }

    public void setFirmwareSourceCertificates(List<String> firmwareSourceCertificates) {
        this.firmwareSourceCertificates = firmwareSourceCertificates;
    }

    public List<String> getAllowedTLSRootCertificates() {
        return allowedTLSRootCertificates;
    }

    public void setAllowedTLSRootCertificates(List<String> allowedTLSRootCertificates) {
        this.allowedTLSRootCertificates = allowedTLSRootCertificates;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(String expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFirmwareSourceID() {
        return firmwareSourceID;
    }

    public void setFirmwareSourceID(String firmwareSourceID) {
        this.firmwareSourceID = firmwareSourceID;
    }

    public List<String> getDeviceCertificate() {
        return deviceCertificate;
    }

    public void setDeviceCertificate(List<String> deviceCertificate) {
        this.deviceCertificate = deviceCertificate;
    }
}
