package com.scenera.nicesecurityapplib.models.data;

public class SceneDataClass {

    private String strSceneMarkID = "";
    private String strSceneDataID = "";
    private String strBase64OfSceneData = "";
    private int iChunkNumber = 0;
    private int iNumberOfChunks = 0;

    public String getStrSceneMarkID() {
        return strSceneMarkID;
    }

    public void setStrSceneMarkID(String strSceneMarkID) {
        this.strSceneMarkID = strSceneMarkID;
    }

    public String getStrSceneDataID() {
        return strSceneDataID;
    }

    public void setStrSceneDataID(String strSceneDataID) {
        this.strSceneDataID = strSceneDataID;
    }

    public String getStrBase64OfSceneData() {
        return strBase64OfSceneData;
    }

    public void setStrBase64OfSceneData(String strBase64OfSceneData) {
        this.strBase64OfSceneData = strBase64OfSceneData;
    }

    public int getiChunkNumber() {
        return iChunkNumber;
    }

    public void setiChunkNumber(int iChunkNumber) {
        this.iChunkNumber = iChunkNumber;
    }

    public int getiNumberOfChunks() {
        return iNumberOfChunks;
    }

    public void setiNumberOfChunks(int iNumberOfChunks) {
        this.iNumberOfChunks = iNumberOfChunks;
    }
}
