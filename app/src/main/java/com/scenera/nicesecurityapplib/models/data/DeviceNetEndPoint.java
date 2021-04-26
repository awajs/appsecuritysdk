package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 28,November,2019
 */
public class DeviceNetEndPoint implements Parcelable
{

    @SerializedName("AccessToken")
    @Expose
    private String accessToken;
    @SerializedName("MQTTServiceLevel")
    @Expose
    private Integer mQTTServiceLevel;
    @SerializedName("ReceiveTopic")
    @Expose
    private String receiveTopic;
    @SerializedName("TransmitTopic")
    @Expose
    private String transmitTopic;
    @SerializedName("URI")
    @Expose
    private String uRI;
    public final static Creator<DeviceNetEndPoint> CREATOR = new Creator<DeviceNetEndPoint>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DeviceNetEndPoint createFromParcel(Parcel in) {
            return new DeviceNetEndPoint(in);
        }

        public DeviceNetEndPoint[] newArray(int size) {
            return (new DeviceNetEndPoint[size]);
        }

    }
            ;

    protected DeviceNetEndPoint(Parcel in) {
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
        this.mQTTServiceLevel = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.receiveTopic = ((String) in.readValue((String.class.getClassLoader())));
        this.transmitTopic = ((String) in.readValue((String.class.getClassLoader())));
        this.uRI = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DeviceNetEndPoint() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getMQTTServiceLevel() {
        return mQTTServiceLevel;
    }

    public void setMQTTServiceLevel(Integer mQTTServiceLevel) {
        this.mQTTServiceLevel = mQTTServiceLevel;
    }

    public String getReceiveTopic() {
        return receiveTopic;
    }

    public void setReceiveTopic(String receiveTopic) {
        this.receiveTopic = receiveTopic;
    }

    public String getTransmitTopic() {
        return transmitTopic;
    }

    public void setTransmitTopic(String transmitTopic) {
        this.transmitTopic = transmitTopic;
    }

    public String getURI() {
        return uRI;
    }

    public void setURI(String uRI) {
        this.uRI = uRI;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accessToken);
        dest.writeValue(mQTTServiceLevel);
        dest.writeValue(receiveTopic);
        dest.writeValue(transmitTopic);
        dest.writeValue(uRI);
    }

    public int describeContents() {
        return 0;
    }
}
