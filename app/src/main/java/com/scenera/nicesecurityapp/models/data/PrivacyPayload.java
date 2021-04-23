package com.scenera.nicesecurityapp.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrivacyPayload implements Parcelable {
    @SerializedName("PrivacyObjectID")
    @Expose
    private String privacyObjectID;
    @SerializedName("StartDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("EndDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("Authentication")
    @Expose
    private Boolean authentication;
    @SerializedName("EndPointID")
    @Expose
    private String endPointID;
    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("MaskedItems")
    @Expose
    private List<String> maskedItems = null;
    @SerializedName("SceneEncryptionKey")
    @Expose
    private SceneEncryptionKey sceneEncryptionKey;
    public final static Creator<Payload> CREATOR = new Creator<Payload>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Payload createFromParcel(Parcel in) {
            return new Payload(in);
        }

        public Payload[] newArray(int size) {
            return (new Payload[size]);
        }

    }
            ;

    protected PrivacyPayload(Parcel in) {
        this.privacyObjectID = ((String) in.readValue((String.class.getClassLoader())));
        this.startDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.endDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.authentication = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.endPointID = ((String) in.readValue((String.class.getClassLoader())));
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.maskedItems, (String.class.getClassLoader()));
        this.sceneEncryptionKey = ((SceneEncryptionKey) in.readValue((SceneEncryptionKey.class.getClassLoader())));
    }

    public PrivacyPayload() {
    }

    public String getPrivacyObjectID() {
        return privacyObjectID;
    }

    public void setPrivacyObjectID(String privacyObjectID) {
        this.privacyObjectID = privacyObjectID;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Boolean getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public String getEndPointID() {
        return endPointID;
    }

    public void setEndPointID(String endPointID) {
        this.endPointID = endPointID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getMaskedItems() {
        return maskedItems;
    }

    public void setMaskedItems(List<String> maskedItems) {
        this.maskedItems = maskedItems;
    }

    public SceneEncryptionKey getSceneEncryptionKey() {
        return sceneEncryptionKey;
    }

    public void setSceneEncryptionKey(SceneEncryptionKey sceneEncryptionKey) {
        this.sceneEncryptionKey = sceneEncryptionKey;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(privacyObjectID);
        dest.writeValue(startDateTime);
        dest.writeValue(endDateTime);
        dest.writeValue(authentication);
        dest.writeValue(endPointID);
        dest.writeValue(version);
        dest.writeList(maskedItems);
        dest.writeValue(sceneEncryptionKey);
    }

    public int describeContents() {
        return 0;
    }
}
