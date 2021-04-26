package com.scenera.nicesecurityapplib.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scenera.nicesecurityapplib.models.data.NICEASEndPointAppSecurity;

/**
 * Created by Ekta Bhatt on 21-Sep-2020
 */
public class AppSecurityObjectResponse implements Parcelable {

    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("NICELARootCertificate")
    @Expose
    private String nICELARootCertificate;
    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("AppID")
    @Expose
    private String appID;
    @SerializedName("AppInstanceCertificate")
    @Expose
    private String appInstanceCertificate;
    @SerializedName("AppDeveloperID")
    @Expose
    private String appDeveloperID;
    @SerializedName("AppInstanceID")
    @Expose
    private String appInstanceID;
    @SerializedName("SecurityLevel")
    @Expose
    private String securityLevel;
    @SerializedName("NICEASEndPoint")
    @Expose
    private NICEASEndPointAppSecurity niceasEndPointAppSecurity;
    public final static Creator<AppSecurityObjectResponse> CREATOR = new Creator<AppSecurityObjectResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AppSecurityObjectResponse createFromParcel(Parcel in) {
            return new AppSecurityObjectResponse(in);
        }

        public AppSecurityObjectResponse[] newArray(int size) {
            return (new AppSecurityObjectResponse[size]);
        }

    }
            ;

    protected AppSecurityObjectResponse(Parcel in) {
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.nICELARootCertificate = ((String) in.readValue((String.class.getClassLoader())));
        this.accountID = ((String) in.readValue((String.class.getClassLoader())));
        this.appID = ((String) in.readValue((String.class.getClassLoader())));
        this.appInstanceCertificate = ((String) in.readValue((String.class.getClassLoader())));
        this.appDeveloperID = ((String) in.readValue((String.class.getClassLoader())));
        this.appInstanceID = ((String) in.readValue((String.class.getClassLoader())));
        this.securityLevel = ((String) in.readValue((String.class.getClassLoader())));
        this.niceasEndPointAppSecurity = ((NICEASEndPointAppSecurity) in.readValue((NICEASEndPointAppSecurity.class.getClassLoader())));
    }

    public AppSecurityObjectResponse() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNICELARootCertificate() {
        return nICELARootCertificate;
    }

    public void setNICELARootCertificate(String nICELARootCertificate) {
        this.nICELARootCertificate = nICELARootCertificate;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppInstanceCertificate() {
        return appInstanceCertificate;
    }

    public void setAppInstanceCertificate(String appInstanceCertificate) {
        this.appInstanceCertificate = appInstanceCertificate;
    }

    public String getAppDeveloperID() {
        return appDeveloperID;
    }

    public void setAppDeveloperID(String appDeveloperID) {
        this.appDeveloperID = appDeveloperID;
    }

    public String getAppInstanceID() {
        return appInstanceID;
    }

    public void setAppInstanceID(String appInstanceID) {
        this.appInstanceID = appInstanceID;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public NICEASEndPointAppSecurity getNICEASEndPoint() {
        return niceasEndPointAppSecurity;
    }

    public void setNICEASEndPoint(NICEASEndPointAppSecurity niceasEndPointAppSecurity) {
        this.niceasEndPointAppSecurity = niceasEndPointAppSecurity;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(version);
        dest.writeValue(nICELARootCertificate);
        dest.writeValue(accountID);
        dest.writeValue(appID);
        dest.writeValue(appInstanceCertificate);
        dest.writeValue(appDeveloperID);
        dest.writeValue(appInstanceID);
        dest.writeValue(securityLevel);
        dest.writeValue(niceasEndPointAppSecurity);
    }

    public int describeContents() {
        return 0;
    }

}

