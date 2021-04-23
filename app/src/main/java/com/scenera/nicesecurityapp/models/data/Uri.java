package com.scenera.nicesecurityapp.models.data;

/**
 * Created by Ekta Bhatt on 21-Jul-2020
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Uri implements Parcelable
{

    @SerializedName("protocol")
    @Expose
    private String protocol;
    @SerializedName("slashes")
    @Expose
    private Boolean slashes;
    @SerializedName("auth")
    @Expose
    private Object auth;
    @SerializedName("host")
    @Expose
    private String host;
    @SerializedName("port")
    @Expose
    private Integer port;
    @SerializedName("hostname")
    @Expose
    private String hostname;
    @SerializedName("hash")
    @Expose
    private Object hash;
    @SerializedName("search")
    @Expose
    private String search;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("pathname")
    @Expose
    private String pathname;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("href")
    @Expose
    private String href;
    public final static Creator<Uri> CREATOR = new Creator<Uri>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Uri createFromParcel(Parcel in) {
            return new Uri(in);
        }

        public Uri[] newArray(int size) {
            return (new Uri[size]);
        }

    }
            ;

    protected Uri(Parcel in) {
        this.protocol = ((String) in.readValue((String.class.getClassLoader())));
        this.slashes = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.auth = ((Object) in.readValue((Object.class.getClassLoader())));
        this.host = ((String) in.readValue((String.class.getClassLoader())));
        this.port = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.hostname = ((String) in.readValue((String.class.getClassLoader())));
        this.hash = ((Object) in.readValue((Object.class.getClassLoader())));
        this.search = ((String) in.readValue((String.class.getClassLoader())));
        this.query = ((String) in.readValue((String.class.getClassLoader())));
        this.pathname = ((String) in.readValue((String.class.getClassLoader())));
        this.path = ((String) in.readValue((String.class.getClassLoader())));
        this.href = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Uri() {
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Boolean getSlashes() {
        return slashes;
    }

    public void setSlashes(Boolean slashes) {
        this.slashes = slashes;
    }

    public Object getAuth() {
        return auth;
    }

    public void setAuth(Object auth) {
        this.auth = auth;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Object getHash() {
        return hash;
    }

    public void setHash(Object hash) {
        this.hash = hash;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(protocol);
        dest.writeValue(slashes);
        dest.writeValue(auth);
        dest.writeValue(host);
        dest.writeValue(port);
        dest.writeValue(hostname);
        dest.writeValue(hash);
        dest.writeValue(search);
        dest.writeValue(query);
        dest.writeValue(pathname);
        dest.writeValue(path);
        dest.writeValue(href);
    }

    public int describeContents() {
        return 0;
    }

}
