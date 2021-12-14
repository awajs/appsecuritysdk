package com.scenera.nicesecurityapplib.models.data;

import org.json.JSONException;
import org.json.JSONObject;

public class SceneMarkValues {

    private String SceneMarkID = "";
    private String TimeStamp = "";
    private String NodeID = "";
    private String PortID = "";
    private String Version = "1.0";
    private String DestinationID = "";
    private String SceneMarkStatus = "Active";
    private String SceneMode = "Human";
    private String EventType = "ItemPresence";
    private String Status = "Upload in Progress";
    private String Thumbnail_SceneDataID = "";
    private String Thumbnail_SceneDataURI = "";
    private String CustomAnalysisID = "";
    private String AnalysisDescription = "Yolo v3 configured to detect Human";
    private String ProcessingStatus = "Detected";
    private String AlgorithmID = "12345678-1234-1234-1234-123456789abc";
    private String NICEItemType = "Human";
    private String CustomItemType = "";
    private int Resolution_Height = 0;
    private int Resolution_Width = 0;
    private int XCoordinate = 289;
    private int YCoordinate = 69;
    private double Probability = 0.0;
    private String DetectedObjects_ThumbnailSceneDataID = "";
    private JSONObject encryptionDictDefualt = new JSONObject();

    public SceneMarkValues(){
        try {
            encryptionDictDefualt.put("EncryptionOn",false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DetectedObjects_Thumbnail_dictEncryption = new Encryption(false,
                "", null,encryptionDictDefualt);
        DetectedObjects_Image_dictEncryption = new Encryption(false,
                "", null,encryptionDictDefualt);
        DetectedObjects_Video_dictEncryption = new Encryption(false,
                "", null,encryptionDictDefualt);
    }

    private Encryption DetectedObjects_Thumbnail_dictEncryption;

    private String DetectedObjects_Image_SceneDataID = "";
    private Encryption DetectedObjects_Image_dictEncryption;

    private String DetectedObjects_Video_SceneDataID = "";
    private Encryption DetectedObjects_Video_dictEncryption;

    private String SceneDataID = "";
    private String SourceNodeID = "";
    private String SourceNodeDescription = "Description of Node that generated SceneData";
    private String Video_Duration = "30";
    private String Thumbnail_DataType = "Thumbnail";
    private String DetectedObjects_Image_DataType = "RGBStill";
    private String DetectedObjects_Video_DataType = "RGBVideo";
    private String Thumbnail_MediaFormat = "JPEG";
    private String DetectedObjects_Image_MediaFormat = "JPEG";
    private String DetectedObjects_Video_MediaFormat = "H.264";
    private int Image_Resolution_Height = 0;
    private int Image_Resolution_Width = 0;
    private String DetectedObjects_Image_SceneDataURI = "";
    private String DetectedObjects_Video_SceneDataURI = "";
    private int Video_Resolution_Height = 0;
    private int Video_Resolution_Width = 0;

    private String SceneDataList_SourceNodeID = "";
    private String SceneDataList_Duration = "";

    public String getSceneMarkID() {
        return SceneMarkID;
    }

    public void setSceneMarkID(String sceneMarkID) {
        SceneMarkID = sceneMarkID;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getNodeID() {
        return NodeID;
    }

    public void setNodeID(String nodeID) {
        NodeID = nodeID;
    }

    public String getPortID() {
        return PortID;
    }

    public void setPortID(String portID) {
        PortID = portID;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getDestinationID() {
        return DestinationID;
    }

    public void setDestinationID(String destinationID) {
        DestinationID = destinationID;
    }

    public String getSceneMarkStatus() {
        return SceneMarkStatus;
    }

    public void setSceneMarkStatus(String sceneMarkStatus) {
        SceneMarkStatus = sceneMarkStatus;
    }

    public String getSceneMode() {
        return SceneMode;
    }

    public void setSceneMode(String sceneMode) {
        SceneMode = sceneMode;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getThumbnail_SceneDataID() {
        return Thumbnail_SceneDataID;
    }

    public void setThumbnail_SceneDataID(String thumbnail_SceneDataID) {
        Thumbnail_SceneDataID = thumbnail_SceneDataID;
    }

    public String getThumbnail_SceneDataURI() {
        return Thumbnail_SceneDataURI;
    }

    public void setThumbnail_SceneDataURI(String thumbnail_SceneDataURI) {
        Thumbnail_SceneDataURI = thumbnail_SceneDataURI;
    }

    public String getCustomAnalysisID() {
        return CustomAnalysisID;
    }

    public void setCustomAnalysisID(String customAnalysisID) {
        CustomAnalysisID = customAnalysisID;
    }

    public String getAnalysisDescription() {
        return AnalysisDescription;
    }

    public void setAnalysisDescription(String analysisDescription) {
        AnalysisDescription = analysisDescription;
    }

    public String getProcessingStatus() {
        return ProcessingStatus;
    }

    public void setProcessingStatus(String processingStatus) {
        ProcessingStatus = processingStatus;
    }

    public String getAlgorithmID() {
        return AlgorithmID;
    }

    public void setAlgorithmID(String algorithmID) {
        AlgorithmID = algorithmID;
    }

    public String getNICEItemType() {
        return NICEItemType;
    }

    public void setNICEItemType(String NICEItemType) {
        this.NICEItemType = NICEItemType;
    }

    public String getCustomItemType() {
        return CustomItemType;
    }

    public void setCustomItemType(String customItemType) {
        CustomItemType = customItemType;
    }

    public int getResolution_Height() {
        return Resolution_Height;
    }

    public void setResolution_Height(int resolution_Height) {
        Resolution_Height = resolution_Height;
    }

    public int getResolution_Width() {
        return Resolution_Width;
    }

    public void setResolution_Width(int resolution_Width) {
        Resolution_Width = resolution_Width;
    }

    public int getXCoordinate() {
        return XCoordinate;
    }

    public void setXCoordinate(int XCoordinate) {
        this.XCoordinate = XCoordinate;
    }

    public int getYCoordinate() {
        return YCoordinate;
    }

    public void setYCoordinate(int YCoordinate) {
        this.YCoordinate = YCoordinate;
    }

    public double getProbability() {
        return Probability;
    }

    public void setProbability(double probability) {
        Probability = probability;
    }

    public String getDetectedObjects_ThumbnailSceneDataID() {
        return DetectedObjects_ThumbnailSceneDataID;
    }

    public void setDetectedObjects_ThumbnailSceneDataID(String detectedObjects_ThumbnailSceneDataID) {
        DetectedObjects_ThumbnailSceneDataID = detectedObjects_ThumbnailSceneDataID;
    }

    public Encryption getDetectedObjects_Thumbnail_dictEncryption() {
        return DetectedObjects_Thumbnail_dictEncryption;
    }

    public void setDetectedObjects_Thumbnail_dictEncryption(Encryption detectedObjects_Thumbnail_dictEncryption) {
        DetectedObjects_Thumbnail_dictEncryption = detectedObjects_Thumbnail_dictEncryption;
    }

    public String getDetectedObjects_Image_SceneDataID() {
        return DetectedObjects_Image_SceneDataID;
    }

    public void setDetectedObjects_Image_SceneDataID(String detectedObjects_Image_SceneDataID) {
        DetectedObjects_Image_SceneDataID = detectedObjects_Image_SceneDataID;
    }

    public Encryption getDetectedObjects_Image_dictEncryption() {
        return DetectedObjects_Image_dictEncryption;
    }

    public void setDetectedObjects_Image_dictEncryption(Encryption detectedObjects_Image_dictEncryption) {
        DetectedObjects_Image_dictEncryption = detectedObjects_Image_dictEncryption;
    }

    public String getDetectedObjects_Video_SceneDataID() {
        return DetectedObjects_Video_SceneDataID;
    }

    public void setDetectedObjects_Video_SceneDataID(String detectedObjects_Video_SceneDataID) {
        DetectedObjects_Video_SceneDataID = detectedObjects_Video_SceneDataID;
    }

    public Encryption getDetectedObjects_Video_dictEncryption() {
        return DetectedObjects_Video_dictEncryption;
    }

    public void setDetectedObjects_Video_dictEncryption(Encryption detectedObjects_Video_dictEncryption) {
        DetectedObjects_Video_dictEncryption = detectedObjects_Video_dictEncryption;
    }

    public String getSceneDataID() {
        return SceneDataID;
    }

    public void setSceneDataID(String sceneDataID) {
        SceneDataID = sceneDataID;
    }

    public String getSourceNodeID() {
        return SourceNodeID;
    }

    public void setSourceNodeID(String sourceNodeID) {
        SourceNodeID = sourceNodeID;
    }

    public String getSourceNodeDescription() {
        return SourceNodeDescription;
    }

    public void setSourceNodeDescription(String sourceNodeDescription) {
        SourceNodeDescription = sourceNodeDescription;
    }

    public String getVideo_Duration() {
        return Video_Duration;
    }

    public void setVideo_Duration(String video_Duration) {
        Video_Duration = video_Duration;
    }

    public String getThumbnail_DataType() {
        return Thumbnail_DataType;
    }

    public void setThumbnail_DataType(String thumbnail_DataType) {
        Thumbnail_DataType = thumbnail_DataType;
    }

    public String getDetectedObjects_Image_DataType() {
        return DetectedObjects_Image_DataType;
    }

    public void setDetectedObjects_Image_DataType(String detectedObjects_Image_DataType) {
        DetectedObjects_Image_DataType = detectedObjects_Image_DataType;
    }

    public String getDetectedObjects_Video_DataType() {
        return DetectedObjects_Video_DataType;
    }

    public void setDetectedObjects_Video_DataType(String detectedObjects_Video_DataType) {
        DetectedObjects_Video_DataType = detectedObjects_Video_DataType;
    }

    public String getThumbnail_MediaFormat() {
        return Thumbnail_MediaFormat;
    }

    public void setThumbnail_MediaFormat(String thumbnail_MediaFormat) {
        Thumbnail_MediaFormat = thumbnail_MediaFormat;
    }

    public String getDetectedObjects_Image_MediaFormat() {
        return DetectedObjects_Image_MediaFormat;
    }

    public void setDetectedObjects_Image_MediaFormat(String detectedObjects_Image_MediaFormat) {
        DetectedObjects_Image_MediaFormat = detectedObjects_Image_MediaFormat;
    }

    public String getDetectedObjects_Video_MediaFormat() {
        return DetectedObjects_Video_MediaFormat;
    }

    public void setDetectedObjects_Video_MediaFormat(String detectedObjects_Video_MediaFormat) {
        DetectedObjects_Video_MediaFormat = detectedObjects_Video_MediaFormat;
    }

    public int getImage_Resolution_Height() {
        return Image_Resolution_Height;
    }

    public void setImage_Resolution_Height(int image_Resolution_Height) {
        Image_Resolution_Height = image_Resolution_Height;
    }

    public int getImage_Resolution_Width() {
        return Image_Resolution_Width;
    }

    public void setImage_Resolution_Width(int image_Resolution_Width) {
        Image_Resolution_Width = image_Resolution_Width;
    }

    public String getDetectedObjects_Image_SceneDataURI() {
        return DetectedObjects_Image_SceneDataURI;
    }

    public void setDetectedObjects_Image_SceneDataURI(String detectedObjects_Image_SceneDataURI) {
        DetectedObjects_Image_SceneDataURI = detectedObjects_Image_SceneDataURI;
    }

    public String getDetectedObjects_Video_SceneDataURI() {
        return DetectedObjects_Video_SceneDataURI;
    }

    public void setDetectedObjects_Video_SceneDataURI(String detectedObjects_Video_SceneDataURI) {
        DetectedObjects_Video_SceneDataURI = detectedObjects_Video_SceneDataURI;
    }

    public int getVideo_Resolution_Height() {
        return Video_Resolution_Height;
    }

    public void setVideo_Resolution_Height(int video_Resolution_Height) {
        Video_Resolution_Height = video_Resolution_Height;
    }

    public int getVideo_Resolution_Width() {
        return Video_Resolution_Width;
    }

    public void setVideo_Resolution_Width(int video_Resolution_Width) {
        Video_Resolution_Width = video_Resolution_Width;
    }

    public String getSceneDataList_SourceNodeID() {
        return SceneDataList_SourceNodeID;
    }

    public void setSceneDataList_SourceNodeID(String sceneDataList_SourceNodeID) {
        SceneDataList_SourceNodeID = sceneDataList_SourceNodeID;
    }

    public String getSceneDataList_Duration() {
        return SceneDataList_Duration;
    }

    public void setSceneDataList_Duration(String sceneDataList_Duration) {
        SceneDataList_Duration = sceneDataList_Duration;
    }
}
