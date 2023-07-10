package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModeForDevice implements Parcelable {

    @SerializedName("SceneMode")
    @Expose
    private String sceneMode;
    @SerializedName("SceneModeConfig")
    @Expose
    private List<SceneModeConfig> sceneModeConfig = null;




    @SerializedName("SceneMarkOutputList")
    @Expose
    private List<SceneMarkOutputListForDevice> sceneMarkOutputListForDevice = null;


    protected ModeForDevice(Parcel in) {
        sceneMode = in.readString();


    }

    public static final Creator<ModeForDevice> CREATOR = new Creator<ModeForDevice>() {
        @Override
        public ModeForDevice createFromParcel(Parcel in) {
            return new ModeForDevice(in);
        }

        @Override
        public ModeForDevice[] newArray(int size) {
            return new ModeForDevice[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sceneMode);
        dest.writeTypedList(sceneMarkOutputListForDevice);
    }

    @Override
    public int describeContents() {
        return 0;
    }



    public String getSceneMode() {
        return sceneMode;
    }

    public void setSceneMode(String sceneMode) {
        this.sceneMode = sceneMode;
    }

    public List<SceneModeConfig> getSceneModeConfig() {
        return sceneModeConfig;
    }

    public void setSceneModeConfig(List<SceneModeConfig> sceneModeConfig) {
        this.sceneModeConfig = sceneModeConfig;
    }

//    public AnalysisResult__1 getAnalysisResult() {
//        return analysisResult;
//    }
//
//    public void setAnalysisResult(AnalysisResult__1 analysisResult) {
//        this.analysisResult = analysisResult;
//    }


//    public List<Scheduling> getScheduling() {
//        return scheduling;
//    }
//
//    public void setScheduling(List<Scheduling> scheduling) {
//        this.scheduling = scheduling;
//    }

    public List<SceneMarkOutputListForDevice> getSceneMarkOutputList() {
        return sceneMarkOutputListForDevice;
    }

    public void setSceneMarkOutputList(List<SceneMarkOutputListForDevice> sceneMarkOutputList) {
        this.sceneMarkOutputListForDevice = sceneMarkOutputList;
    }
}