package vttp2022.ssfminiprojectfinance.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewsList {
    
    private ArrayList<NewsListNews> newsList = new ArrayList<>();
    private String status;

    class NewsListNews {
        private boolean infoComplete;
        private String title;
        private String pubDate;
        private String articleUrl;
        private String imageUrl;
        private String[] stockTickers;

        NewsListNews(Map<String, Object> newsData) {
            Map<String, Object> content = (Map<String, Object>) newsData.get("content");
            this.title = (String)content.get("title");
            this.pubDate = (String)content.get("pubDate");

            Map<String, Object> tmpMap = (Map<String, Object>) content.get("thumbnail");
            List<Object> resolutions = (List<Object>) tmpMap.get("resolutions");
            tmpMap = (Map<String, Object>) resolutions.get(resolutions.size()-1);
            this.imageUrl = (String) tmpMap.get("url");
            
            tmpMap = (Map<String, Object>) content.get("clickThroughUrl");
            if (tmpMap == null) { // at times clickThroughUrl: null
                this.infoComplete = false;
                return;
            }
            this.articleUrl = (String)tmpMap.get("url");

            // info is considered complete if we reached here
            this.infoComplete = true;

            tmpMap = (Map<String, Object>) content.get("finance");
            List<Map<String, String>> stockTickers = (List<Map<String, String>>) tmpMap.get("stockTickers");
            if (stockTickers == null) {
                // still accept info as complete even with missing stock tickers
                return;
            }
            this.stockTickers = new String[stockTickers.size()];
            for (int i = 0; i < stockTickers.size(); i++) {
                this.stockTickers[i] = stockTickers.get(i).get("symbol");
            }
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArticleUrl() {
            return articleUrl;
        }

        public void setArticleUrl(String articleUrl) {
            this.articleUrl = articleUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String[] getStockTickers() {
            return stockTickers;
        }

        public void setStockTickers(String[] stockTickers) {
            this.stockTickers = stockTickers;
        }

        @Override
        public String toString() {
            return "NewsListNews [articleUrl=" + articleUrl + ", imageUrl=" + imageUrl + ", pubDate=" + pubDate
                    + ", stockTickers=" + Arrays.toString(stockTickers) + ", title=" + title + "]";
        }

        public boolean isInfoComplete() {
            return infoComplete;
        }

        public void setInfoComplete(boolean infoComplete) {
            this.infoComplete = infoComplete;
        }

    }

    @SuppressWarnings("unchecked") 
    @JsonProperty("data")
    private void unpackNested(Map<String,Object> data){
        Map<String, Object>  main = (Map<String, Object> ) data.get("main");
        List<Object>  stream = (List<Object>) main.get("stream");
        for (int i = 0; i < stream.size(); i++) {
            Map<String, Object> newsData = (Map<String, Object>) stream.get(i);
            NewsListNews news = new NewsListNews(newsData);
            if (news.infoComplete) this.newsList.add(news);
        }
    }

    public ArrayList<NewsListNews> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<NewsListNews> newsList) {
        this.newsList = newsList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NewsList [newsList=" + newsList + ", status=" + status + "]";
    }

}