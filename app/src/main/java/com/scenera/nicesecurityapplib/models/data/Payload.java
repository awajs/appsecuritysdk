package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekta Bhatt on 5/7/2020.
 */
public class Payload implements Parcelable
{


    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("AppID")
    @Expose
    private String appID;
    @SerializedName("AppInstanceID")
    @Expose
    private String appInstanceID;
    @SerializedName("ControlEndPoints")
    @Expose
    private List<ControlEndPoint> controlEndPoints = null;
    @SerializedName("DataEndPoints")
    @Expose
    private List<DataEndPoint> dataEndPoints = null;
    @SerializedName("NotificationEndPoints")
    @Expose
    private List<NotificationEndPoint> notificationEndPoints = null;
    @SerializedName("SceneModeID")
    @Expose
    private String sceneModeID;
    @SerializedName("NodeID")
    @Expose
    private String nodeID;
    @SerializedName("Inputs")
    @Expose
    private List<Object> inputs = null;
    @SerializedName("Outputs")
    @Expose
    private List<Output> outputs = null;
    @SerializedName("Mode")
    @Expose
    private Mode mode;


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

    protected Payload(Parcel in) {
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.appID = ((String) in.readValue((String.class.getClassLoader())));
        this.appInstanceID = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.controlEndPoints, (ControlEndPoint.class.getClassLoader()));
        in.readList(this.dataEndPoints, (DataEndPoint.class.getClassLoader()));
        in.readList(this.notificationEndPoints, (NotificationEndPoint.class.getClassLoader()));
        this.sceneModeID = ((String) in.readValue((String.class.getClassLoader())));
        this.nodeID = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.inputs, (Object.class.getClassLoader()));
        in.readList(this.outputs, (Output.class.getClassLoader()));
        this.mode = ((Mode) in.readValue((Mode.class.getClassLoader())));
    }

    public Payload() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppInstanceID() {
        return appInstanceID;
    }

    public void setAppInstanceID(String appInstanceID) {
        this.appInstanceID = appInstanceID;
    }

    public List<ControlEndPoint> getControlEndPoints() {
        return controlEndPoints;
    }

    public void setControlEndPoints(List<ControlEndPoint> controlEndPoints) {
        this.controlEndPoints = controlEndPoints;
    }

    public List<DataEndPoint> getDataEndPoints() {
        return dataEndPoints;
    }

    public void setDataEndPoints(List<DataEndPoint> dataEndPoints) {
        this.dataEndPoints = dataEndPoints;
    }
    public List<NotificationEndPoint> getNotificationEndPoints() {
        return notificationEndPoints;
    }

    public void setNotificationEndPoints(List<NotificationEndPoint> notificationEndPoints) {
        this.notificationEndPoints = notificationEndPoints;
    }

    public String getSceneModeID() {
        return sceneModeID;
    }

    public void setSceneModeID(String sceneModeID) {
        this.sceneModeID = sceneModeID;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public List<Object> getInputs() {
        return inputs;
    }

    public void setInputs(List<Object> inputs) {
        this.inputs = inputs;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(version);
        dest.writeValue(appID);
        dest.writeValue(appInstanceID);
        dest.writeList(controlEndPoints);
        dest.writeList(dataEndPoints);
        dest.writeList(notificationEndPoints);
        dest.writeValue(sceneModeID);
        dest.writeValue(nodeID);
        dest.writeList(inputs);
        dest.writeList(outputs);
        dest.writeValue(mode);
    }

    public int describeContents() {
        return 0;
    }


}
