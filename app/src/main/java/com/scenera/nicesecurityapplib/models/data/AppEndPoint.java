package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppEndPoint {
    @SerializedName("APIVersion")
    @Expose
    private String aPIVersion;
    @SerializedName("EndPointID")
    @Expose
    private String endPointID;
    @SerializedName("X.509Certificate")
    @Expose
    private List<String> x509Certificate = null;
    @SerializedName("AccessToken")
    @Expose
    private String accessToken;

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

    public List<String> getX509Certificate() {
        return x509Certificate;
    }

    public void setX509Certificate(List<String> x509Certificate) {
        this.x509Certificate = x509Certificate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
