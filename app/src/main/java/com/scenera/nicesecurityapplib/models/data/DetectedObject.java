package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 15-11-2019.
 */
public class DetectedObject implements Parcelable
{

    @SerializedName("AlgorithmID")
    @Expose
    private String algorithmID;
    @SerializedName("DetectedImage")
    @Expose
    private DetectedImage detectedImage;
    @SerializedName("NICEItemType")
    @Expose
    private String nICEItemType;
    @SerializedName("Resolution")
    @Expose
    private Resolution resolution;
    @SerializedName("XCoordinate")
    @Expose
    private Integer xCoordinate;
    @SerializedName("YCoordinate")
    @Expose
    private Integer yCoordinate;
    public final static Creator<DetectedObject> CREATOR = new Creator<DetectedObject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DetectedObject createFromParcel(Parcel in) {
            return new DetectedObject(in);
        }

        public DetectedObject[] newArray(int size) {
            return (new DetectedObject[size]);
        }

    }
            ;

    protected DetectedObject(Parcel in) {
        this.algorithmID = ((String) in.readValue((String.class.getClassLoader())));
        this.detectedImage = ((DetectedImage) in.readValue((DetectedImage.class.getClassLoader())));
        this.nICEItemType = ((String) in.readValue((String.class.getClassLoader())));
        this.resolution = ((Resolution) in.readValue((Resolution.class.getClassLoader())));
        this.xCoordinate = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.yCoordinate = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public DetectedObject() {
    }

    public String getAlgorithmID() {
        return algorithmID;
    }

    public void setAlgorithmID(String algorithmID) {
        this.algorithmID = algorithmID;
    }

    public DetectedImage getDetectedImage() {
        return detectedImage;
    }

    public void setDetectedImage(DetectedImage detectedImage) {
        this.detectedImage = detectedImage;
    }

    public String getNICEItemType() {
        return nICEItemType;
    }

    public void setNICEItemType(String nICEItemType) {
        this.nICEItemType = nICEItemType;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public Integer getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(algorithmID);
        dest.writeValue(detectedImage);
        dest.writeValue(nICEItemType);
        dest.writeValue(resolution);
        dest.writeValue(xCoordinate);
        dest.writeValue(yCoordinate);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "DetectedObject{" +
                "algorithmID='" + algorithmID + '\'' +
                ", detectedImage=" + detectedImage +
                ", nICEItemType='" + nICEItemType + '\'' +
                ", resolution=" + resolution +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }
}