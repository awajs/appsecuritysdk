package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SceneModeConfig {

    @Expose
    private String customAnalysisStage;
    @SerializedName("AnalysisRegion")
    @Expose
    private List<AnalysisRegion> analysisRegion = null;
//    @SerializedName("AnalysisResult")
//    @Expose
//    private AnalysisResult analysisResult;
    @SerializedName("Resolution")
    @Expose
    private String resolution;
    @SerializedName("Threshold")
    @Expose
    private Double threshold;
    @SerializedName("Scheduling")
    @Expose
    private List<Object> scheduling = null;

    public String getCustomAnalysisStage() {
        return customAnalysisStage;
    }

    public void setCustomAnalysisStage(String customAnalysisStage) {
        this.customAnalysisStage = customAnalysisStage;
    }

    public List<AnalysisRegion> getAnalysisRegion() {
        return analysisRegion;
    }

    public void setAnalysisRegion(List<AnalysisRegion> analysisRegion) {
        this.analysisRegion = analysisRegion;
    }
//
//    public AnalysisResult getAnalysisResult() {
//        return analysisResult;
//    }
//
//    public void setAnalysisResult(AnalysisResult analysisResult) {
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

    public List<Object> getScheduling() {
        return scheduling;
    }

    public void setScheduling(List<Object> scheduling) {
        this.scheduling = scheduling;
    }
}
