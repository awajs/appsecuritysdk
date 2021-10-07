package com.scenera.nicesecurityapplib.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scenera.nicesecurityapplib.models.data.AnalysisList;
import com.scenera.nicesecurityapplib.models.data.DetectedObjectCMF;
import com.scenera.nicesecurityapplib.models.data.SceneDataListCMF;
import com.scenera.nicesecurityapplib.models.data.ThumbnailList;
import com.scenera.nicesecurityapplib.models.data.VersionControl;

import java.util.List;

/**
 * Created by Ekta Bhatt on 5/7/2020.
 */
public class SceneMarkResponseCMF implements Parcelable {

    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("SceneMarkID")
    @Expose
    private String sceneMarkID;
    @SerializedName("DestinationID")
    @Expose
    private List<String> destinationID;
    @SerializedName("SceneMarkStatus")
    @Expose
    private String sceneMarkStatus;
    @SerializedName("NodeID")
    @Expose
    private String nodeID;
    @SerializedName("PortID")
    @Expose
    private String portID;
    @SerializedName("VersionControl")
    @Expose
    private VersionControl versionControl;
    @SerializedName("ThumbnailList")
    @Expose
    private List<ThumbnailList> thumbnailList = null;
    @SerializedName("AnalysisList")
    @Expose
    private List<AnalysisList> analysisList = null;
    @SerializedName("DetectedObjects")
    @Expose
    private List<DetectedObjectCMF> detectedObjects = null;
    @SerializedName("ParentSceneMarks")
    @Expose
    private Object parentSceneMarks;
    @SerializedName("ChildSceneMarks")
    @Expose
    private Object childSceneMarks;
    @SerializedName("SceneDataList")
    @Expose
    private List<SceneDataListCMF> sceneDataList = null;
    @SerializedName("SceneModeConfig")
    @Expose
    private Object sceneModeConfig;
    @SerializedName("DeviceName")
    @Expose
    private String deviceName;
    @SerializedName("DeviceTimeZone")
    @Expose
    private String deviceTimeZone;

    public final static Creator<SceneMarkResponseCMF> CREATOR = new Creator<SceneMarkResponseCMF>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SceneMarkResponseCMF createFromParcel(Parcel in) {
            return new SceneMarkResponseCMF(in);
        }

        public SceneMarkResponseCMF[] newArray(int size) {
            return (new SceneMarkResponseCMF[size]);
        }

    }
            ;

    protected SceneMarkResponseCMF(Parcel in) {
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.timeStamp = ((String) in.readValue((String.class.getClassLoader())));
        this.sceneMarkID = ((String) in.readValue((String.class.getClassLoader())));
        this.destinationID = in.createStringArrayList();
        this.sceneMarkStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.nodeID = ((String) in.readValue((String.class.getClassLoader())));
        this.portID = ((String) in.readValue((String.class.getClassLoader())));
        this.versionControl = ((VersionControl) in.readValue((VersionControl.class.getClassLoader())));
        in.readList(this.thumbnailList, (ThumbnailList.class.getClassLoader()));
        in.readList(this.analysisList, (AnalysisList.class.getClassLoader()));
        in.readList(this.detectedObjects, (DetectedObjectCMF.class.getClassLoader()));
        this.parentSceneMarks = ((Object) in.readValue((Object.class.getClassLoader())));
        this.childSceneMarks = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.sceneDataList, (SceneDataListCMF.class.getClassLoader()));
        this.sceneModeConfig = ((Object) in.readValue((Object.class.getClassLoader())));
        this.deviceName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SceneMarkResponseCMF() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSceneMarkID() {
        return sceneMarkID;
    }

    public void setSceneMarkID(String sceneMarkID) {
        this.sceneMarkID = sceneMarkID;
    }

    public List<String> getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(List<String> destinationID) {
        this.destinationID = destinationID;
    }

    public String getSceneMarkStatus() {
        return sceneMarkStatus;
    }

    public void setSceneMarkStatus(String sceneMarkStatus) {
        this.sceneMarkStatus = sceneMarkStatus;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getPortID() {
        return portID;
    }

    public void setPortID(String portID) {
        this.portID = portID;
    }

    public VersionControl getVersionControl() {
        return versionControl;
    }

    public void setVersionControl(VersionControl versionControl) {
        this.versionControl = versionControl;
    }

    public List<ThumbnailList> getThumbnailList() {
        return thumbnailList;
    }

    public void setThumbnailList(List<ThumbnailList> thumbnailList) {
        this.thumbnailList = thumbnailList;
    }

    public List<AnalysisList> getAnalysisList() {
        return analysisList;
    }

    public void setAnalysisList(List<AnalysisList> analysisList) {
        this.analysisList = analysisList;
    }

    public List<DetectedObjectCMF> getDetectedObjects() {
        return detectedObjects;
    }

    public void setDetectedObjects(List<DetectedObjectCMF> detectedObjects) {
        this.detectedObjects = detectedObjects;
    }

    public Object getParentSceneMarks() {
        return parentSceneMarks;
    }

    public void setParentSceneMarks(Object parentSceneMarks) {
        this.parentSceneMarks = parentSceneMarks;
    }

    public Object getChildSceneMarks() {
        return childSceneMarks;
    }

    public void setChildSceneMarks(Object childSceneMarks) {
        this.childSceneMarks = childSceneMarks;
    }

    public List<SceneDataListCMF> getSceneDataList() {
        return sceneDataList;
    }

    public void setSceneDataList(List<SceneDataListCMF> sceneDataList) {
        this.sceneDataList = sceneDataList;
    }

    public Object getSceneModeConfig() {
        return sceneModeConfig;
    }

    public void setSceneModeConfig(Object sceneModeConfig) {
        this.sceneModeConfig = sceneModeConfig;
    }
    public String getDeviceTimeZone() {
        return deviceTimeZone;
    }

    public void setDeviceTimeZone(String timezone) {
        this.deviceTimeZone = timezone;
    }
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(version);
        dest.writeValue(timeStamp);
        dest.writeValue(sceneMarkID);
        dest.writeStringList(destinationID);
        dest.writeValue(sceneMarkStatus);
        dest.writeValue(nodeID);
        dest.writeValue(portID);
        dest.writeValue(versionControl);
        dest.writeList(thumbnailList);
        dest.writeList(analysisList);
        dest.writeList(detectedObjects);
        dest.writeValue(parentSceneMarks);
        dest.writeValue(childSceneMarks);
        dest.writeList(sceneDataList);
        dest.writeValue(sceneModeConfig);
        dest.writeValue(deviceName);
    }

    public int describeContents() {
        return 0;
    }
   /* @SerializedName("AnalysisList")
    @Expose
    private List<AnalysisList> analysisList = null;
    @SerializedName("DestinationID")
    @Expose
    private String destinationID;
    @SerializedName("DetectedObjects")
    @Expose
    private List<DetectedObjectCMF> detectedObjects = null;
    @SerializedName("NodeID")
    @Expose
    private String nodeID;
    @SerializedName("PortID")
    @Expose
    private String portID;
    @SerializedName("SceneDataList")
    @Expose
    private List<SceneDataListCMF> sceneDataList = null;
    @SerializedName("SceneMarkID")
    @Expose
    private String sceneMarkID;
    @SerializedName("SceneMarkStatus")
    @Expose
    private String sceneMarkStatus;
    @SerializedName("ThumbnailList")
    @Expose
    private List<ThumbnailList> thumbnailList = null;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("DeviceName")
    @Expose
    private String deviceName;
    @SerializedName("DeviceTimeZone")
    @Expose
    private String deviceTimeZone;

    public final static Parcelable.Creator<SceneMarkResponseCMF> CREATOR = new Creator<SceneMarkResponseCMF>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SceneMarkResponseCMF createFromParcel(Parcel in) {
            return new SceneMarkResponseCMF(in);
        }

        public SceneMarkResponseCMF[] newArray(int size) {
            return (new SceneMarkResponseCMF[size]);
        }

    }
            ;

    protected SceneMarkResponseCMF(Parcel in) {
        in.readList(this.analysisList, (com.scenera.nicesecurityapp.models.data.AnalysisList.class.getClassLoader()));
        this.destinationID = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.detectedObjects, (com.scenera.nicesecurityapp.models.data.DetectedObjectCMF.class.getClassLoader()));
        this.nodeID = ((String) in.readValue((String.class.getClassLoader())));
        this.portID = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.sceneDataList, (com.scenera.nicesecurityapp.models.data.SceneDataListCMF.class.getClassLoader()));
        this.sceneMarkID = ((String) in.readValue((String.class.getClassLoader())));
        this.sceneMarkStatus = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.thumbnailList, (com.scenera.nicesecurityapp.models.data.ThumbnailList.class.getClassLoader()));
        this.timeStamp = ((String) in.readValue((String.class.getClassLoader())));
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.deviceName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SceneMarkResponseCMF() {
    }

    public List<AnalysisList> getAnalysisList() {
        return analysisList;
    }

    public void setAnalysisList(List<AnalysisList> analysisList) {
        this.analysisList = analysisList;
    }

    public String getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(String destinationID) {
        this.destinationID = destinationID;
    }

    public List<DetectedObjectCMF> getDetectedObjects() {
        return detectedObjects;
    }

    public void setDetectedObjects(List<DetectedObjectCMF> detectedObjects) {
        this.detectedObjects = detectedObjects;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getPortID() {
        return portID;
    }

    public void setPortID(String portID) {
        this.portID = portID;
    }

    public List<SceneDataListCMF> getSceneDataList() {
        return sceneDataList;
    }

    public void setSceneDataList(List<SceneDataListCMF> sceneDataList) {
        this.sceneDataList = sceneDataList;
    }

    public String getSceneMarkID() {
        return sceneMarkID;
    }

    public void setSceneMarkID(String sceneMarkID) {
        this.sceneMarkID = sceneMarkID;
    }

    public String getSceneMarkStatus() {
        return sceneMarkStatus;
    }

    public void setSceneMarkStatus(String sceneMarkStatus) {
        this.sceneMarkStatus = sceneMarkStatus;
    }

    public List<ThumbnailList> getThumbnailList() {
        return thumbnailList;
    }

    public void setThumbnailList(List<ThumbnailList> thumbnailList) {
        this.thumbnailList = thumbnailList;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(analysisList);
        dest.writeValue(destinationID);
        dest.writeList(detectedObjects);
        dest.writeValue(nodeID);
        dest.writeValue(portID);
        dest.writeList(sceneDataList);
        dest.writeValue(sceneMarkID);
        dest.writeValue(sceneMarkStatus);
        dest.writeList(thumbnailList);
        dest.writeValue(timeStamp);
        dest.writeValue(version);
        dest.writeValue(deviceName);
    }

    public int describeContents() {
        return 0;
    }
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceTimeZone() {
        return deviceTimeZone;
    }

    public void setDeviceTimeZone(String timezone) {
        this.deviceTimeZone = timezone;
    }*/
}