package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchemePrivacy implements Parcelable {
    @SerializedName("Protocol")
    @Expose
    private String protocol;
    @SerializedName("Authority")
    @Expose
    private String authority;
    @SerializedName("AccessToken")
    @Expose
    private String accessToken;
    @SerializedName("Role")
    @Expose
    private String role;
    @SerializedName("ValidationKey")
    @Expose
    private Object validationKey;
    public final static Creator<SchemePrivacy> CREATOR = new Creator<SchemePrivacy>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SchemePrivacy createFromParcel(Parcel in) {
            return new SchemePrivacy(in);
        }

        public SchemePrivacy[] newArray(int size) {
            return (new SchemePrivacy[size]);
        }

    }
            ;

    protected SchemePrivacy(Parcel in) {
        this.protocol = ((String) in.readValue((String.class.getClassLoader())));
        this.authority = ((String) in.readValue((String.class.getClassLoader())));
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
        this.role = ((String) in.readValue((String.class.getClassLoader())));
        this.validationKey = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public SchemePrivacy() {
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Object getValidationKey() {
        return validationKey;
    }

    public void setValidationKey(Object validationKey) {
        this.validationKey = validationKey;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(protocol);
        dest.writeValue(authority);
        dest.writeValue(accessToken);
        dest.writeValue(role);
        dest.writeValue(validationKey);
    }

    public int describeContents() {
        return 0;
    }

}
