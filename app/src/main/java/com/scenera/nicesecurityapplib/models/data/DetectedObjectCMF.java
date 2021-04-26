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
    private List<String> relatedSceneData = null;
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

    protected DetectedObjectCMF(Parcel in) {
        algorithmID = in.readString();
        boundingBox = in.readParcelable(BoundingBox.class.getClassLoader());
        nICEItemType = in.readString();
        customItemType = in.readString();
        relatedSceneData = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(algorithmID);
        dest.writeParcelable(boundingBox, flags);
        dest.writeString(nICEItemType);
        dest.writeString(customItemType);
        dest.writeStringList(relatedSceneData);
    }

    public DetectedObjectCMF() {
    }

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

    public List<String> getRelatedSceneData() {
        return relatedSceneData;
    }

    public void setRelatedSceneData(List<String> relatedSceneData) {
        this.relatedSceneData = relatedSceneData;
    }


    public String getCustomItemType() {
        return customItemType;
    }

    public void setCustomItemType(String customItemType) {
        this.customItemType = customItemType;
    }

    public int describeContents() {
        return 0;
    }


}
