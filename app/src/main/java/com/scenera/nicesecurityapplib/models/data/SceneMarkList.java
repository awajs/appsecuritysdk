package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekta Bhatt on 15-11-2019.
 */
public class SceneMarkList implements Parcelable {

    @SerializedName("SceneMarkID")
    @Expose
    private String sceneMarkID;
    @SerializedName("NodeID")
    @Expose
    private String nodeID;
    @SerializedName("SceneMarkURI")
    @Expose
    private String sceneMarkURI;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("ItemIdentities")
    @Expose
    private List<ItemIdentities> itemIdentities;
    @SerializedName("SceneDataThumbnail")
    @Expose
    private SceneDataThumbnail sceneDataThumbnail;
    public final static Creator<SceneMarkList> CREATOR = new Creator<SceneMarkList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SceneMarkList createFromParcel(Parcel in) {
            return new SceneMarkList(in);
        }

        public SceneMarkList[] newArray(int size) {
            return (new SceneMarkList[size]);
        }

    }
            ;

    protected SceneMarkList(Parcel in) {
        this.sceneMarkID = ((String) in.readValue((String.class.getClassLoader())));
        this.nodeID = ((String) in.readValue((String.class.getClassLoader())));
        this.sceneMarkURI = ((String) in.readValue((String.class.getClassLoader())));
        this.timeStamp = ((String) in.readValue((String.class.getClassLoader())));
        this.sceneDataThumbnail = ((SceneDataThumbnail) in.readValue((SceneDataThumbnail.class.getClassLoader())));
        in.readList(this.itemIdentities, (ItemIdentities.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public SceneMarkList() {
    }

    /**
     *
     * @param timeStamp
     * @param sceneDataThumbnail
     * @param sceneMarkURI
     * @param nodeID
     * @param sceneMarkID
     */
    public SceneMarkList(String sceneMarkID, String nodeID, String sceneMarkURI, String timeStamp, SceneDataThumbnail sceneDataThumbnail) {
        super();
        this.sceneMarkID = sceneMarkID;
        this.nodeID = nodeID;
        this.sceneMarkURI = sceneMarkURI;
        this.timeStamp = timeStamp;
        this.sceneDataThumbnail = sceneDataThumbnail;
    }

    public String getSceneMarkID() {
        return sceneMarkID;
    }

    public void setSceneMarkID(String sceneMarkID) {
        this.sceneMarkID = sceneMarkID;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getSceneMarkURI() {
        return sceneMarkURI;
    }

    public void setSceneMarkURI(String sceneMarkURI) {
        this.sceneMarkURI = sceneMarkURI;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public SceneDataThumbnail getSceneDataThumbnail() {
        return sceneDataThumbnail;
    }

    public void setSceneDataThumbnail(SceneDataThumbnail sceneDataThumbnail) {
        this.sceneDataThumbnail = sceneDataThumbnail;
    }

    public List<ItemIdentities> getItemIdentities() {
        return itemIdentities;
    }

    public void setItemIdentities(List<ItemIdentities> itemIdentities) {
        this.itemIdentities = itemIdentities;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sceneMarkID);
        dest.writeValue(nodeID);
        dest.writeValue(sceneMarkURI);
        dest.writeValue(timeStamp);
        dest.writeValue(sceneDataThumbnail);
        dest.writeList(itemIdentities);
    }

    public int describeContents() {
        return 0;
    }
}

