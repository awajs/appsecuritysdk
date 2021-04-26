package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.SerializedName;

public class CMFRequestBody {

    @SerializedName("Version")
    private String version;
    @SerializedName("MessageType")
    private String messageType;
    @SerializedName("SourceEndPointID")
    private String sourceEndPointID;
    @SerializedName("DestinationEndPointID")
    private String destinationEndPointID;
    @SerializedName("DateTimeStamp")
    private String dateTimeStamp;
    @SerializedName("CommandID")
    private String commandID;
    @SerializedName("CommandType")
    private String commandType;
    @SerializedName("Payload")
    private String payload;
    @SerializedName("AccessToken")
    private String accessToken;

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

    public String getCommandID() {
        return commandID;
    }

    public void setCommandID(String commandID) {
        this.commandID = commandID;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
