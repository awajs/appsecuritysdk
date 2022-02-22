package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceSecurityObject implements Parcelable {

    @SerializedName("FirmwareSourceCertificate")
    @Expose
    private String firmwareSourceCertificate;
    @SerializedName("DeviceCertificate")
    @Expose
    private String deviceCertificate;
    @SerializedName("DevicePassword")
    @Expose
    private String devicePassword;
    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("DeviceID")
    @Expose
    private String deviceID;
    @SerializedName("DevicePrivateKey")
    @Expose
    private DevicePrivateKeyEncypted devicePrivateKeyEncypted;
    @SerializedName("AllowedTLSRootCertificates")
    @Expose
    private List<Object> allowedTLSRootCertificates = null;
    @SerializedName("NICELARootCertificate")
    @Expose
    private String nICELARootCertificate;
    @SerializedName("NICELAEndPoint")
    @Expose
    private NetEndPointAppControl nICELAEndPoint;
    @SerializedName("MasterIssuerID")
    @Expose
    private String masterIssuerID;
    @SerializedName("FirmwareSourceID")
    @Expose
    private String firmwareSourceID;

    protected DeviceSecurityObject(Parcel in) {
        firmwareSourceCertificate = in.readString();
        deviceCertificate = in.readString();
        devicePassword = in.readString();
        version = in.readString();
        deviceID = in.readString();
        nICELARootCertificate = in.readString();
        nICELAEndPoint = in.readParcelable(NetEndPointAppControl.class.getClassLoader());
        masterIssuerID = in.readString();
        firmwareSourceID = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firmwareSourceCertificate);
        dest.writeString(deviceCertificate);
        dest.writeString(devicePassword);
        dest.writeString(version);
        dest.writeString(deviceID);
        dest.writeString(nICELARootCertificate);
        dest.writeParcelable(nICELAEndPoint, flags);
        dest.writeString(masterIssuerID);
        dest.writeString(firmwareSourceID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeviceSecurityObject> CREATOR = new Creator<DeviceSecurityObject>() {
        @Override
        public DeviceSecurityObject createFromParcel(Parcel in) {
            return new DeviceSecurityObject(in);
        }

        @Override
        public DeviceSecurityObject[] newArray(int size) {
            return new DeviceSecurityObject[size];
        }
    };

    public String getFirmwareSourceCertificate() {
        return firmwareSourceCertificate;
    }

    public void setFirmwareSourceCertificate(String firmwareSourceCertificate) {
        this.firmwareSourceCertificate = firmwareSourceCertificate;
    }

    public String getDeviceCertificate() {
        return deviceCertificate;
    }

    public void setDeviceCertificate(String deviceCertificate) {
        this.deviceCertificate = deviceCertificate;
    }

    public String getDevicePassword() {
        return devicePassword;
    }

    public void setDevicePassword(String devicePassword) {
        this.devicePassword = devicePassword;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public DevicePrivateKeyEncypted getDevicePrivateKey() {
        return devicePrivateKeyEncypted;
    }

    public void setDevicePrivateKey(DevicePrivateKeyEncypted devicePrivateKeyEncypted) {
        this.devicePrivateKeyEncypted = devicePrivateKeyEncypted;
    }

    public List<Object> getAllowedTLSRootCertificates() {
        return allowedTLSRootCertificates;
    }

    public void setAllowedTLSRootCertificates(List<Object> allowedTLSRootCertificates) {
        this.allowedTLSRootCertificates = allowedTLSRootCertificates;
    }

    public String getNICELARootCertificate() {
        return nICELARootCertificate;
    }

    public void setNICELARootCertificate(String nICELARootCertificate) {
        this.nICELARootCertificate = nICELARootCertificate;
    }

    public NetEndPointAppControl getNICELAEndPoint() {
        return nICELAEndPoint;
    }

    public void setNICELAEndPoint(NetEndPointAppControl nICELAEndPoint) {
        this.nICELAEndPoint = nICELAEndPoint;
    }

    public String getMasterIssuerID() {
        return masterIssuerID;
    }

    public void setMasterIssuerID(String masterIssuerID) {
        this.masterIssuerID = masterIssuerID;
    }

    public String getFirmwareSourceID() {
        return firmwareSourceID;
    }

    public void setFirmwareSourceID(String firmwareSourceID) {
        this.firmwareSourceID = firmwareSourceID;
    }

}
