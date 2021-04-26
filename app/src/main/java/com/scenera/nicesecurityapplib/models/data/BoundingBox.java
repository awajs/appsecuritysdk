package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 10-Jun-2020
 */
public class BoundingBox implements Parcelable {

    @SerializedName("Height")
    @Expose
    private Integer height;
    @SerializedName("Width")
    @Expose
    private Integer width;
    @SerializedName("XCoordinate")
    @Expose
    private Integer xCoordinate;
    @SerializedName("YCoordinate")
    @Expose
    private Integer yCoordinate;
    public final static Creator<BoundingBox> CREATOR = new Creator<BoundingBox>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BoundingBox createFromParcel(Parcel in) {
            return new BoundingBox(in);
        }

        public BoundingBox[] newArray(int size) {
            return (new BoundingBox[size]);
        }

    }
            ;

    protected BoundingBox(Parcel in) {
        this.height = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.width = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.xCoordinate = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.yCoordinate = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public BoundingBox() {
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
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
        dest.writeValue(height);
        dest.writeValue(width);
        dest.writeValue(xCoordinate);
        dest.writeValue(yCoordinate);
    }

    public int describeContents() {
        return 0;
    }

}
