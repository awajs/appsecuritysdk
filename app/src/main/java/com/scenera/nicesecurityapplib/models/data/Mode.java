package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mode implements Parcelable {

    @SerializedName("SceneMode")
    @Expose
    private String sceneMode;
    @SerializedName("SceneModeConfig")
    @Expose
    private List<SceneModeConfig> sceneModeConfig = null;
//    @SerializedName("AnalysisResult")
//    @Expose
//    private AnalysisResult__1 analysisResult;
    @SerializedName("Resolution")
    @Expose
    private String resolution;
    @SerializedName("Threshold")
    @Expose
    private Double threshold;
//    @SerializedName("Scheduling")
//    @Expose
//    private List<Scheduling> scheduling = null;
    @SerializedName("SceneMarkOutputList")
    @Expose
    private List<ControlEndPoint> sceneMarkOutputList = null;

    protected Mode(Parcel in) {
        sceneMode = in.readString();
        resolution = in.readString();
        if (in.readByte() == 0) {
            threshold = null;
        } else {
            threshold = in.readDouble();
        }
        sceneMarkOutputList = in.createTypedArrayList(ControlEndPoint.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sceneMode);
        dest.writeString(resolution);
        if (threshold == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(threshold);
        }
        dest.writeTypedList(sceneMarkOutputList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Mode> CREATOR = new Creator<Mode>() {
        @Override
        public Mode createFromParcel(Parcel in) {
            return new Mode(in);
        }

        @Override
        public Mode[] newArray(int size) {
            return new Mode[size];
        }
    };

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

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

//    public List<Scheduling> getScheduling() {
//        return scheduling;
//    }
//
//    public void setScheduling(List<Scheduling> scheduling) {
//        this.scheduling = scheduling;
//    }

    public List<ControlEndPoint> getSceneMarkOutputList() {
        return sceneMarkOutputList;
    }

    public void setSceneMarkOutputList(List<ControlEndPoint> sceneMarkOutputList) {
        this.sceneMarkOutputList = sceneMarkOutputList;
    }
}
