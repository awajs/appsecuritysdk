package com.scenera.nicesecurityapplib.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scenera.nicesecurityapplib.models.data.Payload;

/**
 * Created by Ekta Bhatt on 5/7/2020.
 */
public class SceneMarkManifestResponseCMF implements Parcelable
{

    @SerializedName("DateTimeStamp")
    @Expose
    private String dateTimeStamp;
    @SerializedName("DestinationEndPointID")
    @Expose
    private String destinationEndPointID;
    @SerializedName("EncryptionOn")
    @Expose
    private Boolean encryptionOn;
    @SerializedName("MessageType")
    @Expose
    private String messageType;
    @SerializedName("Payload")
    @Expose
    private Payload payload;
    @SerializedName("ReplyErrorCode")
    @Expose
    private Integer replyErrorCode;
    @SerializedName("ReplyID")
    @Expose
    private Integer replyID;
    @SerializedName("ReplyStatusCode")
    @Expose
    private Integer replyStatusCode;
    @SerializedName("ReplyStatusMessage")
    @Expose
    private String replyStatusMessage;
    @SerializedName("SourceEndPointID")
    @Expose
    private String sourceEndPointID;
    @SerializedName("Version")
    @Expose
    private String version;

    public final static Creator<SceneMarkManifestResponseCMF> CREATOR = new Creator<SceneMarkManifestResponseCMF>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SceneMarkManifestResponseCMF createFromParcel(Parcel in) {
            return new SceneMarkManifestResponseCMF(in);
        }

        public SceneMarkManifestResponseCMF[] newArray(int size) {
            return (new SceneMarkManifestResponseCMF[size]);
        }

    }
            ;

    protected SceneMarkManifestResponseCMF(Parcel in) {
        this.dateTimeStamp = ((String) in.readValue((String.class.getClassLoader())));
        this.destinationEndPointID = ((String) in.readValue((String.class.getClassLoader())));
        this.encryptionOn = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.messageType = ((String) in.readValue((String.class.getClassLoader())));
        this.payload = ((Payload) in.readValue((Payload.class.getClassLoader())));
        this.replyErrorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.replyID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.replyStatusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.replyStatusMessage = ((String) in.readValue((String.class.getClassLoader())));
        this.sourceEndPointID = ((String) in.readValue((String.class.getClassLoader())));
        this.version = ((String) in.readValue((String.class.getClassLoader())));

    }

    /**
     * No args constructor for use in serialization
     *
     */
    public SceneMarkManifestResponseCMF() {
    }

    /**
     *
     * @param replyStatusMessage
     * @param encryptionOn
     * @param messageType
     * @param payload
     * @param replyErrorCode
     * @param sourceEndPointID
     * @param replyID
     * @param dateTimeStamp
     * @param replyStatusCode
     * @param destinationEndPointID
     * @param version
     */
    public SceneMarkManifestResponseCMF(String dateTimeStamp, String destinationEndPointID, Boolean encryptionOn, String messageType, Payload payload, Integer replyErrorCode, Integer replyID, Integer replyStatusCode, String replyStatusMessage, String sourceEndPointID, String version) {
        super();
        this.dateTimeStamp = dateTimeStamp;
        this.destinationEndPointID = destinationEndPointID;
        this.encryptionOn = encryptionOn;
        this.messageType = messageType;
        this.payload = payload;
        this.replyErrorCode = replyErrorCode;
        this.replyID = replyID;
        this.replyStatusCode = replyStatusCode;
        this.replyStatusMessage = replyStatusMessage;
        this.sourceEndPointID = sourceEndPointID;
        this.version = version;
    }

    public String getDateTimeStamp() {
        return dateTimeStamp;
    }

    public void setDateTimeStamp(String dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
    }

    public String getDestinationEndPointID() {
        return destinationEndPointID;
    }

    public void setDestinationEndPointID(String destinationEndPointID) {
        this.destinationEndPointID = destinationEndPointID;
    }

    public Boolean getEncryptionOn() {
        return encryptionOn;
    }

    public void setEncryptionOn(Boolean encryptionOn) {
        this.encryptionOn = encryptionOn;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public Integer getReplyErrorCode() {
        return replyErrorCode;
    }

    public void setReplyErrorCode(Integer replyErrorCode) {
        this.replyErrorCode = replyErrorCode;
    }

    public Integer getReplyID() {
        return replyID;
    }

    public void setReplyID(Integer replyID) {
        this.replyID = replyID;
    }

    public Integer getReplyStatusCode() {
        return replyStatusCode;
    }

    public void setReplyStatusCode(Integer replyStatusCode) {
        this.replyStatusCode = replyStatusCode;
    }

    public String getReplyStatusMessage() {
        return replyStatusMessage;
    }

    public void setReplyStatusMessage(String replyStatusMessage) {
        this.replyStatusMessage = replyStatusMessage;
    }

    public String getSourceEndPointID() {
        return sourceEndPointID;
    }

    public void setSourceEndPointID(String sourceEndPointID) {
        this.sourceEndPointID = sourceEndPointID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dateTimeStamp);
        dest.writeValue(destinationEndPointID);
        dest.writeValue(encryptionOn);
        dest.writeValue(messageType);
        dest.writeValue(payload);
        dest.writeValue(replyErrorCode);
        dest.writeValue(replyID);
        dest.writeValue(replyStatusCode);
        dest.writeValue(replyStatusMessage);
        dest.writeValue(sourceEndPointID);
        dest.writeValue(version);

    }

    public int describeContents() {
        return 0;
    }


}
