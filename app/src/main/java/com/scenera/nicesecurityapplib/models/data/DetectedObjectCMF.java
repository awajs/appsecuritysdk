package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekta Bhatt on 5/7/2020.
 */
public class DetectedObjectCMF implements Parcelable
{
    @SerializedName("AlgorithmID")
    @Expose
    private String algorithmID;
    @SerializedName("BoundingBox")
    @Expose
    private BoundingBox boundingBox;
    @SerializedName("NICEItemType")
    @Expose
    private String nICEItemType;
    @SerializedName("CustomItemType")
    @Expose
    private String customItemType;
    @SerializedName("RelatedSceneData")
    @Expose
    private String relatedSceneData = null;


    public DetectedObjectCMF() {
    }

    protected DetectedObjectCMF(Parcel in) {
        algorithmID = in.readString();
        boundingBox = in.readParcelable(BoundingBox.class.getClassLoader());
        nICEItemType = in.readString();
        customItemType = in.readString();
        relatedSceneData = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(algorithmID);
        dest.writeParcelable(boundingBox, flags);
        dest.writeString(nICEItemType);
        dest.writeString(customItemType);
        dest.writeString(relatedSceneData);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DetectedObjectCMF> CREATOR = new Creator<DetectedObjectCMF>() {
        @Override
        public DetectedObjectCMF createFromParcel(Parcel in) {
            return new DetectedObjectCMF(in);
        }

        @Override
        public DetectedObjectCMF[] newArray(int size) {
            return new DetectedObjectCMF[size];
        }
    };

    public String getAlgorithmID() {
        return algorithmID;
    }

    public void setAlgorithmID(String algorithmID) {
        this.algorithmID = algorithmID;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String getNICEItemType() {
        return nICEItemType;
    }

    public void setNICEItemType(String nICEItemType) {
        this.nICEItemType = nICEItemType;
    }

    public String getRelatedSceneData() {
        return relatedSceneData;
    }

    public void setRelatedSceneData(String relatedSceneData) {
        this.relatedSceneData = relatedSceneData;
    }


    public String getCustomItemType() {
        return customItemType;
    }

    public void setCustomItemType(String customItemType) {
        this.customItemType = customItemType;
    }



}
