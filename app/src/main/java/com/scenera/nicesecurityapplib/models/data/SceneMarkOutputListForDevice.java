package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SceneMarkOutputListForDevice implements Parcelable {


    @SerializedName("SceneMarkOutputEndPoint")
    @Expose
    private SceneMarkOutputEndPointForDevice sceneMarkOutputEndPointForDevices = null;


    protected SceneMarkOutputListForDevice(Parcel in) {
        sceneMarkOutputEndPointForDevices = in.readParcelable(SceneMarkOutputEndPointForDevice.class.getClassLoader());
    }

    public static final Creator<SceneMarkOutputListForDevice> CREATOR = new Creator<SceneMarkOutputListForDevice>() {
        @Override
        public SceneMarkOutputListForDevice createFromParcel(Parcel in) {
            return new SceneMarkOutputListForDevice(in);
        }

        @Override
        public SceneMarkOutputListForDevice[] newArray(int size) {
            return new SceneMarkOutputListForDevice[size];
        }
    };

    public SceneMarkOutputEndPointForDevice getSceneMarkOutputEndPointForDevices() {
        return sceneMarkOutputEndPointForDevices;
    }

    public void setSceneMarkOutputEndPointForDevices(SceneMarkOutputEndPointForDevice sceneMarkOutputEndPointForDevices) {
        this.sceneMarkOutputEndPointForDevices = sceneMarkOutputEndPointForDevices;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        this.sceneMarkOutputEndPointForDevices = ((SceneMarkOutputEndPointForDevice) dest.readValue((SceneMarkOutputEndPointForDevice.class.getClassLoader())));
    }
}
