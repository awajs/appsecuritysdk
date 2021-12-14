package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetectedObjectsClass {

    @SerializedName("strSceneDataID")
    @Expose
    private String strSceneDataID;

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("Probability")
    @Expose
    private double Probability;

    @SerializedName("xmin")
    @Expose
    private int xmin;

    @SerializedName("ymin")
    @Expose
    private int ymin;

    @SerializedName("xmax")
    @Expose
    private int xmax;

    @SerializedName("ymax")
    @Expose
    private int ymax;

    @SerializedName("Width")
    @Expose
    private int Width;

    @SerializedName("Height")
    @Expose
    private int Height;

    @SerializedName("strBase64OfSceneData")
    @Expose
    private String strBase64OfSceneData;

    public String getStrSceneDataID() {
        return strSceneDataID;
    }

    public void setStrSceneDataID(String strSceneDataID) {
        this.strSceneDataID = strSceneDataID;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getProbability() {
        return Probability;
    }

    public void setProbability(double probability) {
        Probability = probability;
    }

    public int getXmin() {
        return xmin;
    }

    public void setXmin(int xmin) {
        this.xmin = xmin;
    }

    public int getYmin() {
        return ymin;
    }

    public void setYmin(int ymin) {
        this.ymin = ymin;
    }

    public int getXmax() {
        return xmax;
    }

    public void setXmax(int xmax) {
        this.xmax = xmax;
    }

    public int getYmax() {
        return ymax;
    }

    public void setYmax(int ymax) {
        this.ymax = ymax;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public String getStrBase64OfSceneData() {
        return strBase64OfSceneData;
    }

    public void setStrBase64OfSceneData(String strBase64OfSceneData) {
        this.strBase64OfSceneData = strBase64OfSceneData;
    }
}
