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
    private double height;
    @SerializedName("Width")
    @Expose
    private double width;
    @SerializedName("XCoordinate")
    @Expose
    private double xCoordinate;
    @SerializedName("YCoordinate")
    @Expose
    private double yCoordinate;

    public BoundingBox() {
    }

    protected BoundingBox(Parcel in) {
        height = in.readDouble();
        width = in.readDouble();
        xCoordinate = in.readDouble();
        yCoordinate = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(height);
        dest.writeDouble(width);
        dest.writeDouble(xCoordinate);
        dest.writeDouble(yCoordinate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BoundingBox> CREATOR = new Creator<BoundingBox>() {
        @Override
        public BoundingBox createFromParcel(Parcel in) {
            return new BoundingBox(in);
        }

        @Override
        public BoundingBox[] newArray(int size) {
            return new BoundingBox[size];
        }
    };

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
