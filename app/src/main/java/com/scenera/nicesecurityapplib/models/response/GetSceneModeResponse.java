package com.scenera.nicesecurityapplib.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scenera.nicesecurityapplib.models.data.Payload;

public class GetSceneModeResponse {

    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("MessageType")
    @Expose
    private String messageType;
    @SerializedName("SourceEndPointID")
    @Expose
    private String sourceEndPointID;
    @SerializedName("DestinationEndPointID")
    @Expose
    private String destinationEndPointID;
    @SerializedName("DateTimeStamp")
    @Expose
    private String dateTimeStamp;
    @SerializedName("ReplyID")
    @Expose
    private Integer replyID;
    @SerializedName("Payload")
    @Expose
    private Payload payload;
    @SerializedName("ReplyStatusMessage")
    @Expose
    private String replyStatusMessage;
    @SerializedName("ReplyStatusCode")
    @Expose
    private Integer replyStatusCode;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSourceEndPointID() {
        return sourceEndPointID;
    }

    public void setSourceEndPointID(String sourceEndPointID) {
        this.sourceEndPointID = sourceEndPointID;
    }

    public String getDestinationEndPointID() {
        return destinationEndPointID;
    }

    public void setDestinationEndPointID(String destinationEndPointID) {
        this.destinationEndPointID = destinationEndPointID;
    }

    public String getDateTimeStamp() {
        return dateTimeStamp;
    }

    public void setDateTimeStamp(String dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
    }

    public Integer getReplyID() {
        return replyID;
    }

    public void setReplyID(Integer replyID) {
        this.replyID = replyID;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getReplyStatusMessage() {
        return replyStatusMessage;
    }

    public void setReplyStatusMessage(String replyStatusMessage) {
        this.replyStatusMessage = replyStatusMessage;
    }

    public Integer getReplyStatusCode() {
        return replyStatusCode;
    }

    public void setReplyStatusCode(Integer replyStatusCode) {
        this.replyStatusCode = replyStatusCode;
    }

}
