package com.scenera.nicesecurityapp.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 21-Sep-2020
 */
public class SchemeAppControlObject implements Parcelable
{


    @SerializedName("Protocol")
    @Expose
    private String protocol;
    @SerializedName("Authority")
    @Expose
    private String authority;
    @SerializedName("Role")
    @Expose
    private String role;
    @SerializedName("AccessToken")
    @Expose
    private String accessToken;
    public final static Creator<SchemeAppControlObject> CREATOR = new Creator<SchemeAppControlObject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SchemeAppControlObject createFromParcel(Parcel in) {
            return new SchemeAppControlObject(in);
        }

        public SchemeAppControlObject[] newArray(int size) {
            return (new SchemeAppControlObject[size]);
        }

    }
            ;

    protected SchemeAppControlObject(Parcel in) {
        this.protocol = ((String) in.readValue((String.class.getClassLoader())));
        this.authority = ((String) in.readValue((String.class.getClassLoader())));
        this.role = ((String) in.readValue((String.class.getClassLoader())));
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
    }
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public SchemeAppControlObject() {
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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
        dest.writeValue(authority);
        dest.writeValue(role);
        dest.writeValue(accessToken);
    }

    public int describeContents() {
        return 0;
    }

}
