package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PayloadObjectForDevice implements Parcelable {

    @SerializedName("SceneModeID")
    @Expose
    private String sceneModeID;

    @SerializedName("NodeID")
    @Expose
    private String nodeID;


    @SerializedName("Version")
    @Expose
    private String version;


    @SerializedName("Inputs")
    @Expose
    private List<Object> inputs = null;

    @SerializedName("Outputs")
    @Expose
    private List<Output> outputs = null;

    @SerializedName("Mode")
    @Expose
    private ModeForDevice mode;

    public final static Creator<PayloadObjectForDevice> CREATOR = new Creator<PayloadObjectForDevice>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PayloadObjectForDevice createFromParcel(Parcel in) {
            return new PayloadObjectForDevice(in);
        }

        public PayloadObjectForDevice[] newArray(int size) {
            return (new PayloadObjectForDevice[size]);
        }

    };

    protected PayloadObjectForDevice(Parcel in) {
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.sceneModeID = ((String) in.readValue((String.class.getClassLoader())));
        this.nodeID = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.inputs, (Object.class.getClassLoader()));
        in.readList(this.outputs, (Output.class.getClassLoader()));
        this.mode = ((ModeForDevice) in.readValue((ModeForDevice.class.getClassLoader())));
    }

    public PayloadObjectForDevice() {
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


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public ModeForDevice getMode() {
        return mode;
    }

    public void setMode(ModeForDevice mode) {
        this.mode = mode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(version);
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
