package com.scenera.nicesecurityapp.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scenera.nicesecurityapp.models.data.Payload;

/**
 * Created by Ekta Bhatt on 21-Sep-2020
 */
public class AppConrolObjectResponse implements Parcelable
{


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
    @SerializedName("ReplyStatusCode")
    @Expose
    private Integer replyStatusCode;
    @SerializedName("ReplyStatusMessage")
    @Expose
    private String replyStatusMessage;
    @SerializedName("Payload")
    @Expose
    private Object payload;
    public final static Creator<AppConrolObjectResponse> CREATOR = new Creator<AppConrolObjectResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AppConrolObjectResponse createFromParcel(Parcel in) {
            return new AppConrolObjectResponse(in);
        }

        public AppConrolObjectResponse[] newArray(int size) {
            return (new AppConrolObjectResponse[size]);
        }

    }
            ;

    protected AppConrolObjectResponse(Parcel in) {
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.messageType = ((String) in.readValue((String.class.getClassLoader())));
        this.sourceEndPointID = ((String) in.readValue((String.class.getClassLoader())));
        this.destinationEndPointID = ((String) in.readValue((String.class.getClassLoader())));
        this.dateTimeStamp = ((String) in.readValue((String.class.getClassLoader())));
        this.replyID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.replyStatusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.replyStatusMessage = ((String) in.readValue((String.class.getClassLoader())));
        this.payload = this.payload instanceof String ? in.readString() : ((Payload) in.readValue((Payload.class.getClassLoader())));
    }

    public AppConrolObjectResponse() {
    }

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

    public Payload getPayload() {
        return new Gson().fromJson(new Gson().toJson(payload), Payload.class);
    }

    public String getStringPayload() {
        return String.valueOf(payload);
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(version);
        dest.writeValue(messageType);
        dest.writeValue(sourceEndPointID);
        dest.writeValue(destinationEndPointID);
        dest.writeValue(dateTimeStamp);
        dest.writeValue(replyID);
        dest.writeValue(replyStatusCode);
        dest.writeValue(replyStatusMessage);
        dest.writeValue(payload);
    }

    public int describeContents() {
        return 0;
    }

}