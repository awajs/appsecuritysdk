package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnalysisRegion {
    @SerializedName("XCoord")
    @Expose
    private Integer xCoord;
    @SerializedName("YCoord")
    @Expose
    private Integer yCoord;

    public Integer getXCoord() {
        return xCoord;
    }

    public void setXCoord(Integer xCoord) {
        this.xCoord = xCoord;
    }

    public Integer getYCoord() {
        return yCoord;
    }

    public void setYCoord(Integer yCoord) {
        this.yCoord = yCoord;
    }
}
