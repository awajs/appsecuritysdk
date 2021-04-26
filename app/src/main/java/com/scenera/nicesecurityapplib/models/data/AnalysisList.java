package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    public final static Creator<AnalysisList> CREATOR = new Creator<AnalysisList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AnalysisList createFromParcel(Parcel in) {
            return new AnalysisList(in);
        }

        public AnalysisList[] newArray(int size) {
            return (new AnalysisList[size]);
        }

    }
            ;

    protected AnalysisList(Parcel in) {
        this.analysisDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.customAnalysisID = ((String) in.readValue((String.class.getClassLoader())));
        this.processingStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.sceneMode = ((String) in.readValue((String.class.getClassLoader())));
        this.versionNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(analysisDescription);
        dest.writeValue(customAnalysisID);
        dest.writeValue(processingStatus);
        dest.writeValue(sceneMode);
        dest.writeValue(versionNumber);
    }

    public int describeContents() {
        return 0;
    }

}