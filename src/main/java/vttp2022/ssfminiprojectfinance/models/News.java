package vttp2022.ssfminiprojectfinance.models;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class News {
    
    private String title;
    private String url;
    private String[] authorsDisplayName;
    private String providerDisplayName;
    private String pubDate;
    private String summary;
    private String imageURL;
    private Integer originalHeight;
    private Integer originalWidth;
    private String status;

@SuppressWarnings("unchecked") 
@JsonProperty("data")
private void unpackNested(Map<String,Object> data){
    List<Object> contents = (List<Object>)data.get("contents");
    Map<String, Object> content = (Map<String, Object>) contents.get(0);
    content = (Map<String, Object>) content.get("content");
    this.title = (String)content.get("title");
    Map<String, Object> canonicalUrl = (Map<String, Object>) content.get("canonicalUrl");
    this.url = (String) canonicalUrl.get("url");
    
    List<Object> authors = (List<Object>)content.get("authors");
    Map<String, Object> author;
    this.authorsDisplayName = new String[authors.size()];
    for (int i = 0; i < authors.size(); i++) {
        author = (Map<String, Object>) authors.get(i);
        author = (Map<String, Object>) author.get("author");
        this.authorsDisplayName[i] = (String) author.get("displayName");
    }

    Map<String, Object> provider = (Map<String, Object>) content.get("provider");
    this.providerDisplayName = (String)provider.get("displayName");

    this.pubDate = (String)content.get("pubDate");
    this.summary = (String)content.get("summary");

    Map<String, Object> tmp = (Map<String, Object>) content.get("body");
    tmp = (Map<String, Object>) tmp.get("data");
    tmp = (Map<String, Object>) tmp.get("partnerData");
    tmp = (Map<String, Object>) tmp.get("cover");
    tmp = (Map<String, Object>) tmp.get("image");
    this.imageURL = (String) tmp.get("originalUrl");
    this.originalHeight = (Integer) tmp.get("originalHeight");
    this.originalWidth = (Integer) tmp.get("originalWidth");
}

@Override
public String toString() {
    return "News [authorsDisplayName=" + getAuthorsDisplayName() + ", originalHeight=" + originalHeight + ", imageUrl="
            + imageURL + ", originalWidth=" + originalWidth + ", providerDisplayName=" + providerDisplayName
            + ", pubDate=" + pubDate + ", summary=" + summary + ", title=" + title + "]";
}


public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getProviderDisplayName() {
    return providerDisplayName;
}

public void setProviderDisplayName(String providerDisplayName) {
    this.providerDisplayName = providerDisplayName;
}

public String getPubDate() {
    return pubDate;
}

public void setPubDate(String pubDate) {
    this.pubDate = pubDate;
}

public String getSummary() {
    return summary;
}

public void setSummary(String summary) {
    this.summary = summary;
}


public Integer getOriginalHeight() {
    return originalHeight;
}

public void setOriginalHeight(Integer originalHeight) {
    this.originalHeight = originalHeight;
}

public Integer getOriginalWidth() {
    return originalWidth;
}

public void setOriginalWidth(Integer originalWidth) {
    this.originalWidth = originalWidth;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

public String getAuthorsDisplayName() {
    String res = "";
    for (int i = 0; i < authorsDisplayName.length; i++) {
        if (i > 0 && i < authorsDisplayName.length) {
            res += ",";
        }
        res += authorsDisplayName[i];
    }
    return res;
}

public void setAuthorsDisplayName(String[] authorsDisplayName) {
    this.authorsDisplayName = authorsDisplayName;
}

public String getUrl() {
    return url;
}

public void setUrl(String url) {
    this.url = url;
}

public String getImageURL() {
    return imageURL;
}

public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
}


}
