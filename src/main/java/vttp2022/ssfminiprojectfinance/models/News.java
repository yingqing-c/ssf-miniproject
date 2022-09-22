package vttp2022.ssfminiprojectfinance.models;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class News {
    
    private String id;
    private String title;
    private String url;
    private String[] authorsDisplayName;
    private String providerDisplayName;
    private String pubDate;
    private String summary;
    private String imageURL;
    private String status;
    private String markup;

@SuppressWarnings("unchecked") 
@JsonProperty("data")
private void unpackNested(Map<String,Object> data){
    List<Object> contents = (List<Object>)data.get("contents");
    Map<String, Object> content = (Map<String, Object>) contents.get(0);
    content = (Map<String, Object>) content.get("content");
    this.id = (String) content.get("id");
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
    this.markup = (String) tmp.get("markup");
    tmp = (Map<String, Object>) tmp.get("data");
    tmp = (Map<String, Object>) tmp.get("partnerData");
    tmp = (Map<String, Object>) tmp.get("cover");
    tmp = (Map<String, Object>) tmp.get("image");
    this.imageURL = (String) tmp.get("originalUrl");
}

public String getTitle() {
    return title;
}

public String getUrl() {
    return url;
}

public String[] getAuthorsDisplayName() {
    return authorsDisplayName;
}

public String getProviderDisplayName() {
    return providerDisplayName;
}

public String getPubDate() {
    
    return pubDate;
}

public String getSummary() {
    return summary;
}

public String getImageURL() {
    return imageURL;
}


public String getStatus() {
    return status;
}

public String getMarkup() {
    return markup;
}

public String getId() {
    return id;
}

@Override
public String toString() {
    return "News [authorsDisplayName=" + Arrays.toString(authorsDisplayName) + ", id=" + id + ", imageURL=" + imageURL
            + ", markup=" + markup + ", providerDisplayName=" + providerDisplayName + ", pubDate=" + pubDate
            + ", status=" + status + ", summary=" + summary + ", title=" + title + ", url=" + url + "]";
}

}
