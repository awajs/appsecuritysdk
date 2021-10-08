package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekta Bhatt on 5/7/2020.
 */
public class AnalysisList implements Parcelable
{

    @SerializedName("AnalysisDescription")
    @Expose
    private String analysisDescription;
    @SerializedName("CustomAnalysisID")
    @Expose
    private String customAnalysisID;
    @SerializedName("ProcessingStatus")
    @Expose
    private String processingStatus;
    @SerializedName("SceneMode")
    @Expose
    private String sceneMode;
    @SerializedName("VersionNumber")
    @Expose
    private Integer versionNumber;
    @SerializedName("DetectedObjects")
    @Expose
    private List<DetectedObjectCMF> detectedObjects = null;



    /**
     * No args constructor for use in serialization
     *
     */
    public AnalysisList() {
    }

    /**
     *
     * @param customAnalysisID
     * @param processingStatus
     * @param analysisDescription
     * @param versionNumber
     * @param sceneMode
     */
    public AnalysisList(String analysisDescription, String customAnalysisID, String processingStatus, String sceneMode, Integer versionNumber) {
        super();
        this.analysisDescription = analysisDescription;
        this.customAnalysisID = customAnalysisID;
        this.processingStatus = processingStatus;
        this.sceneMode = sceneMode;
        this.versionNumber = versionNumber;
    }

    protected AnalysisList(Parcel in) {
        analysisDescription = in.readString();
        customAnalysisID = in.readString();
        processingStatus = in.readString();
        sceneMode = in.readString();
        if (in.readByte() == 0) {
            versionNumber = null;
        } else {
            versionNumber = in.readInt();
        }
        detectedObjects = in.createTypedArrayList(DetectedObjectCMF.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(analysisDescription);
        dest.writeString(customAnalysisID);
        dest.writeString(processingStatus);
        dest.writeString(sceneMode);
        if (versionNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(versionNumber);
        }
        dest.writeTypedList(detectedObjects);
    }

    public static final Creator<AnalysisList> CREATOR = new Creator<AnalysisList>() {
        @Override
        public AnalysisList createFromParcel(Parcel in) {
            return new AnalysisList(in);
        }

        @Override
        public AnalysisList[] newArray(int size) {
            return new AnalysisList[size];
        }
    };

    public List<DetectedObjectCMF> getDetectedObjects() {
        return detectedObjects;
    }

    public void setDetectedObjects(List<DetectedObjectCMF> detectedObjects) {
        this.detectedObjects = detectedObjects;
    }

    public String getAnalysisDescription() {
        return analysisDescription;
    }

    public void setAnalysisDescription(String analysisDescription) {
        this.analysisDescription = analysisDescription;
    }

    public String getCustomAnalysisID() {
        return customAnalysisID;
    }

    public void setCustomAnalysisID(String customAnalysisID) {
        this.customAnalysisID = customAnalysisID;
    }

    public String getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(String processingStatus) {
        this.processingStatus = processingStatus;
    }

    public String getSceneMode() {
        return sceneMode;
    }

    public void setSceneMode(String sceneMode) {
        this.sceneMode = sceneMode;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }


    public int describeContents() {
        return 0;
    }

}