package com.scenera.nicesecurityapplib.models.data;

import java.util.ArrayList;
import java.util.List;

public class SceneDataValues {

    private String Version = "";
    private String DataID = "";
    private String FileType = "";
    private String FileName = "";
    private String PathURI = "";
    private int Section = 0;
    private int LastSection = 0;
    private String HashMethod = "";
    private String OriginalFileHash = "";
    private String SectionBase64 = "";
    private List<String> RelatedSceneMarks = new ArrayList<>();

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getDataID() {
        return DataID;
    }

    public void setDataID(String dataID) {
        DataID = dataID;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getPathURI() {
        return PathURI;
    }

    public void setPathURI(String pathURI) {
        PathURI = pathURI;
    }

    public int getSection() {
        return Section;
    }

    public void setSection(int section) {
        Section = section;
    }

    public int getLastSection() {
        return LastSection;
    }

    public void setLastSection(int lastSection) {
        LastSection = lastSection;
    }

    public String getHashMethod() {
        return HashMethod;
    }

    public void setHashMethod(String hashMethod) {
        HashMethod = hashMethod;
    }

    public String getOriginalFileHash() {
        return OriginalFileHash;
    }

    public void setOriginalFileHash(String originalFileHash) {
        OriginalFileHash = originalFileHash;
    }

    public String getSectionBase64() {
        return SectionBase64;
    }

    public void setSectionBase64(String sectionBase64) {
        SectionBase64 = sectionBase64;
    }

    public List<String> getRelatedSceneMarks() {
        return RelatedSceneMarks;
    }

    public void setRelatedSceneMarks(List<String> relatedSceneMarks) {
        RelatedSceneMarks = relatedSceneMarks;
    }
}
