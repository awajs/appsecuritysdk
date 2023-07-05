package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppEndPointAppSecurityForDevice implements Parcelable
{

    @SerializedName("APIVersion")
    @Expose
    private String aPIVersion;
    @SerializedName("EndPointID")
    @Expose
    private String endPointID;
    @SerializedName("AccessToken")
    @Expose
    private String accessToken;
    @SerializedName("X.509Certificate")
    @Expose
    private List<Object> x509Certificate = null;

    public final static Creator<AppEndPointAppSecurity> CREATOR = new Creator<AppEndPointAppSecurity>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AppEndPointAppSecurity createFromParcel(Parcel in) {
            return new AppEndPointAppSecurity(in);
        }

        public AppEndPointAppSecurity[] newArray(int size) {
            return (new AppEndPointAppSecurity[size]);
        }

    }
            ;

    protected AppEndPointAppSecurityForDevice(Parcel in) {
        this.aPIVersion = ((String) in.readValue((String.class.getClassLoader())));
        this.endPointID = ((String) in.readValue((String.class.getClassLoader())));
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AppEndPointAppSecurityForDevice() {
    }

    public String getAPIVersion() {
        return aPIVersion;
    }

    public void setAPIVersion(String aPIVersion) {
        this.aPIVersion = aPIVersion;
    }

    public String getEndPointID() {
        return endPointID;
    }

    public void setEndPointID(String endPointID) {
        this.endPointID = endPointID;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getaPIVersion() {
        return aPIVersion;
    }

    public void setaPIVersion(String aPIVersion) {
        this.aPIVersion = aPIVersion;
    }

    public List<Object> getX509Certificate() {
        return x509Certificate;
    }

    public void setX509Certificate(List<Object> x509Certificate) {
        this.x509Certificate = x509Certificate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(aPIVersion);
        dest.writeValue(endPointID);
        dest.writeValue(accessToken);
        dest.writeValue(x509Certificate);
    }

    public int describeContents() {
        return 0;
    }
}
