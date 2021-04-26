package com.scenera.nicesecurityapplib.models.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekta Bhatt on 23-Sep-2020
 */
public class NetEndPointAppSecurity implements Parcelable
{


    @SerializedName("APIVersion")
    @Expose
    private String aPIVersion;
    @SerializedName("EndPointID")
    @Expose
    private String endPointID;
    @SerializedName("Scheme")
    @Expose
    private List<SchemeAppSecurity> scheme = null;
    public final static Creator<NetEndPointAppSecurity> CREATOR = new Creator<NetEndPointAppSecurity>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NetEndPointAppSecurity createFromParcel(Parcel in) {
            return new NetEndPointAppSecurity(in);
        }

        public NetEndPointAppSecurity[] newArray(int size) {
            return (new NetEndPointAppSecurity[size]);
        }

    }
            ;

    protected NetEndPointAppSecurity(Parcel in) {
        this.aPIVersion = ((String) in.readValue((String.class.getClassLoader())));
        this.endPointID = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.scheme, (SchemeAppSecurity.class.getClassLoader()));
    }

    public NetEndPointAppSecurity() {
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

    public List<SchemeAppSecurity> getScheme() {
        return scheme;
    }

    public void setScheme(List<SchemeAppSecurity> scheme) {
        this.scheme = scheme;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(aPIVersion);
        dest.writeValue(endPointID);
        dest.writeList(scheme);
    }

    public int describeContents() {
        return 0;
    }

}
