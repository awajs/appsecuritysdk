package com.scenera.nicesecurityapp.models.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 23-Sep-2020
 */
public class SchemeAppSecurity implements Parcelable {

    @SerializedName("Protocol")
    @Expose
    private String protocol;
    @SerializedName("AccessToken")
    @Expose
    private String accessToken;
    @SerializedName("Authority")
    @Expose
    private String authority;
    @SerializedName("Role")
    @Expose
    private String role;
    public final static Creator<SchemeAppSecurity> CREATOR = new Creator<SchemeAppSecurity>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SchemeAppSecurity createFromParcel(Parcel in) {
            return new SchemeAppSecurity(in);
        }

        public SchemeAppSecurity[] newArray(int size) {
            return (new SchemeAppSecurity[size]);
        }

    };

    protected SchemeAppSecurity(Parcel in) {
        this.protocol = ((String) in.readValue((String.class.getClassLoader())));
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
        this.authority = ((String) in.readValue((String.class.getClassLoader())));
        this.role = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SchemeAppSecurity() {
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(protocol);
        dest.writeValue(accessToken);
        dest.writeValue(authority);
        dest.writeValue(role);
    }

    public int describeContents() {
        return 0;
    }
}

