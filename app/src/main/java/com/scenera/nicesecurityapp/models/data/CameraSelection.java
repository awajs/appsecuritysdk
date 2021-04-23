package com.scenera.nicesecurityapp.models.data;

/**
 * Created by Ekta Bhatt on 17,March,2020
 */
public class CameraSelection {
    private String cameraName;
    private int totalCount;

    public CameraSelection() {
    }

    public CameraSelection(String cameraName, int totalCount) {
        this.cameraName = cameraName;
        this.totalCount = totalCount;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "CameraSelection{" +
                "cameraName='" + cameraName + '\'' +
                ", totalCount=" + totalCount +
                '}';
    }
}
