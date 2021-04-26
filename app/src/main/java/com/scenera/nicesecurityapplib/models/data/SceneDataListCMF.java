package com.scenera.nicesecurityapplib.models.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 5/7/2020.
 */
public class SceneDataListCMF implements Parcelable
{
    @SerializedName("DataType")
    @Expose
    private String dataType;
    @SerializedName("Encryption")
    @Expose
    private Encryption encryption;
    @SerializedName("MediaFormat")
    @Expose
    private String mediaFormat;
    @SerializedName("SceneDataID")
    @Expose
    private String sceneDataID;
    @SerializedName("SceneDataURI")
    @Expose
    private String sceneDataURI;
    @SerializedName("SourceNodeDescription")
    @Expose
    private String sourceNodeDescription;
    @SerializedName("SourceNodeID")
    @Expose
    private String sourceNodeID;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("Duration")
    @Expose
    private Integer duration;
    public final static Creator<SceneDataListCMF> CREATOR = new Creator<SceneDataListCMF>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SceneDataListCMF createFromParcel(Parcel in) {
            return new SceneDataListCMF(in);
        }

        public SceneDataListCMF[] newArray(int size) {
            return (new SceneDataListCMF[size]);
        }

    }
            ;

    protected SceneDataListCMF(Parcel in) {
        this.dataType = ((String) in.readValue((String.class.getClassLoader())));
        this.encryption = ((Encryption) in.readValue((Encryption.class.getClassLoader())));
        this.mediaFormat = ((String) in.readValue((String.class.getClassLoader())));
        this.sceneDataID = ((String) in.readValue((String.class.getClassLoader())));
        this.sceneDataURI = ((String) in.readValue((String.class.getClassLoader())));
        this.sourceNodeDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.sourceNodeID = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.timeStamp = ((String) in.readValue((String.class.getClassLoader())));
        this.duration = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public SceneDataListCMF() {
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public String getMediaFormat() {
        return mediaFormat;
    }

    public void setMediaFormat(String mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    public String getSceneDataID() {
        return sceneDataID;
    }

    public void setSceneDataID(String sceneDataID) {
        this.sceneDataID = sceneDataID;
    }

    public String getSceneDataURI() {
        return sceneDataURI;
    }

    public void setSceneDataURI(String sceneDataURI) {
        this.sceneDataURI = sceneDataURI;
    }

    public String getSourceNodeDescription() {
        return sourceNodeDescription;
    }

    public void setSourceNodeDescription(String sourceNodeDescription) {
        this.sourceNodeDescription = sourceNodeDescription;
    }

    public String getSourceNodeID() {
        return sourceNodeID;
    }

    public void setSourceNodeID(String sourceNodeID) {
        this.sourceNodeID = sourceNodeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dataType);
        dest.writeValue(encryption);
        dest.writeValue(mediaFormat);
        dest.writeValue(sceneDataID);
        dest.writeValue(sceneDataURI);
        dest.writeValue(sourceNodeDescription);
        dest.writeValue(sourceNodeID);
        dest.writeValue(status);
        dest.writeValue(timeStamp);
        dest.writeValue(duration);
    }

    public int describeContents() {
        return 0;
    }
}
