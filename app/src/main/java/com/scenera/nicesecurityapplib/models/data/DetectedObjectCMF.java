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
        this.algorithmID = ((String) in.readValue((String.class.getClassLoader())));
        this.boundingBox = ((BoundingBox) in.readValue((BoundingBox.class.getClassLoader())));
        this.nICEItemType = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.relatedSceneData, (String.class.getClassLoader()));
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(algorithmID);
        dest.writeValue(boundingBox);
        dest.writeValue(nICEItemType);
        dest.writeList(relatedSceneData);
    }

    public int describeContents() {
        return 0;
    }


}
