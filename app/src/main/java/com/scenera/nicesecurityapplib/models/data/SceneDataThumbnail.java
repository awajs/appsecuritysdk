package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SceneDataThumbnail implements Parcelable
{

    @SerializedName("NICEItemTypes")
    @Expose
    private List<String> sceneModeDetectionType = null;
    @SerializedName("SceneDataThumbnailURI")
    @Expose
    private String sceneDataThumbnailURI;
    @SerializedName("EncryptionOn")
    @Expose
    private Boolean encryptionOn;
    @SerializedName("SceneEncryptionKeyID")
    @Expose
    private String sceneEncryptionKeyID;
    public final static Creator<SceneDataThumbnail> CREATOR = new Creator<SceneDataThumbnail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SceneDataThumbnail createFromParcel(Parcel in) {
            return new SceneDataThumbnail(in);
        }

        public SceneDataThumbnail[] newArray(int size) {
            return (new SceneDataThumbnail[size]);
        }

    }
            ;

    protected SceneDataThumbnail(Parcel in) {
        this.sceneModeDetectionType = in.createStringArrayList();
        this.sceneDataThumbnailURI = ((String) in.readValue((String.class.getClassLoader())));
        this.encryptionOn = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.sceneEncryptionKeyID = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public SceneDataThumbnail() {
    }

    /**
     *
     * @param sceneModeDetectionType
     * @param sceneDataThumbnailURI
     * @param encryptionOn
     * @param sceneEncryptionKeyID
     */
    public SceneDataThumbnail(List<String> sceneModeDetectionType, String sceneDataThumbnailURI, Boolean encryptionOn, String sceneEncryptionKeyID) {
        super();
        this.sceneModeDetectionType = sceneModeDetectionType;
        this.sceneDataThumbnailURI = sceneDataThumbnailURI;
        this.encryptionOn = encryptionOn;
        this.sceneEncryptionKeyID = sceneEncryptionKeyID;
    }

    public List<String> getSceneModeDetectionType() {
        return sceneModeDetectionType;
    }

    public void setSceneModeDetectionType(List<String> sceneModeDetectionType) {
        this.sceneModeDetectionType = sceneModeDetectionType;
    }

    public String getSceneDataThumbnailURI() {
        return sceneDataThumbnailURI;
    }

    public void setSceneDataThumbnailURI(String sceneDataThumbnailURI) {
        this.sceneDataThumbnailURI = sceneDataThumbnailURI;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(sceneModeDetectionType);
        dest.writeValue(sceneDataThumbnailURI);
        dest.writeValue(encryptionOn);
        dest.writeValue(sceneEncryptionKeyID);
    }

    public int describeContents() {
        return 0;
    }

}
