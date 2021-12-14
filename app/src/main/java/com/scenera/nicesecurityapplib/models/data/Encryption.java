package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by Ekta Bhatt on 15-11-2019.
 */
public class Encryption implements Parcelable
{

    @SerializedName("EncryptionOn")
    @Expose
    private Boolean encryptionOn;
    @SerializedName("SceneEncryptionKeyID")
    @Expose
    private String sceneEncryptionKeyID;
    @SerializedName("PrivacyServerEndPoint")
    @Expose
    private PrivacyServerEndPoint privacyServerEndPoint;
    private JSONObject dictEncryption;
    public final static Creator<Encryption> CREATOR = new Creator<Encryption>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Encryption createFromParcel(Parcel in) {
            return new Encryption(in);
        }

        public Encryption[] newArray(int size) {
            return (new Encryption[size]);
        }

    }
            ;

    protected Encryption(Parcel in) {
        this.encryptionOn = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.sceneEncryptionKeyID = ((String) in.readValue((String.class.getClassLoader())));
        this.privacyServerEndPoint = ((PrivacyServerEndPoint) in.readValue((PrivacyServerEndPoint.class.getClassLoader())));
        this.dictEncryption = ((JSONObject) in.readValue((JSONObject.class.getClassLoader())));
    }

    public Encryption() {
    }

    public Encryption(boolean EncryptionOn, String SceneEncryptionKeyID,
                      PrivacyServerEndPoint PrivacyServerEndPoint, JSONObject dictEncryption) {
        this.encryptionOn = EncryptionOn;
        this.sceneEncryptionKeyID = SceneEncryptionKeyID;
        this.privacyServerEndPoint = PrivacyServerEndPoint;
        this.dictEncryption = dictEncryption;

    }

    public Boolean getEncryptionOn() {
        return encryptionOn;
    }

    public void setEncryptionOn(Boolean encryptionOn) {
        this.encryptionOn = encryptionOn;
    }

    public String getSceneEncryptionKeyID() {
        return sceneEncryptionKeyID;
    }

    public void setSceneEncryptionKeyID(String sceneEncryptionKeyID) {
        this.sceneEncryptionKeyID = sceneEncryptionKeyID;
    }

    public PrivacyServerEndPoint getPrivacyServerEndPoint() {
        return privacyServerEndPoint;
    }

    public void setPrivacyServerEndPoint(PrivacyServerEndPoint privacyServerEndPoint) {
        this.privacyServerEndPoint = privacyServerEndPoint;
    }

    public JSONObject getDictEncryption() {
        return dictEncryption;
    }

    public void setDictEncryption(JSONObject dictEncryption) {
        this.dictEncryption = dictEncryption;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(encryptionOn);
        dest.writeValue(sceneEncryptionKeyID);
        dest.writeValue(privacyServerEndPoint);
        dest.writeValue(dictEncryption);
    }

    public int describeContents() {
        return 0;
    }

}