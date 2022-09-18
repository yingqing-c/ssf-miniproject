package vttp2022.ssfminiprojectfinance.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import vttp2022.ssfminiprojectfinance.models.News;
import vttp2022.ssfminiprojectfinance.models.NewsList;
import vttp2022.ssfminiprojectfinance.models.User;

@Service
public class FinanceAPIService {
    private static final Logger logger = LoggerFactory.getLogger(FinanceAPIService.class);
    
    // API call from yh finance
    public static final String URL = "https://yh-finance.p.rapidapi.com/";

    // inject in the key
    @Value("${RAPID_API_KEY}")
    private String apiKey;
     

    public News getNews(String uuid) {
        
        ResponseEntity<String> resp;
        News response = null;

        String newsUrl = UriComponentsBuilder.fromUriString(URL)
                            .path("news/v2/get-details")
                            .queryParam("uuid", uuid)                          
                            .toUriString();

        logger.info(newsUrl);

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "yh-finance.p.rapidapi.com");
        HttpEntity request = new HttpEntity(headers);
        resp = template.exchange(newsUrl,
                               HttpMethod.GET,
                               request,
                               String.class,
                               1);

        // Using Jackson to map json string
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            response = mapper.readValue(resp.getBody(), News.class);
            logger.info("response: ", response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response;
    }
    public NewsList getNewsList() {

        HttpResponse<String> resp;
        NewsList response = null;

        String newsUrl = UriComponentsBuilder.fromUriString(URL)
                            .path("news/v2/list")                         
                            .toUriString();

        logger.info(newsUrl);

        // RestTemplate template = new RestTemplate();
        // HttpHeaders headers = new HttpHeaders();
        // headers.set("X-RapidAPI-Key", apiKey);
        // headers.set("X-RapidAPI-Host", "yh-finance.p.rapidapi.com");
        // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // headers.setContentType(MediaType.APPLICATION_JSON);
        // HttpEntity entity = new HttpEntity(headers);
        
        // resp = template.postForObject(newsUrl, entity, String.class);

        HttpRequest req = HttpRequest.newBuilder()
                            .POST(HttpRequest.BodyPublishers.ofString(""))
                            .uri(URI.create(newsUrl))
                            .setHeader("X-RapidAPI-Key", apiKey)
                            .setHeader("X-RapidAPI-Host", "yh-finance.p.rapidapi.com")
                            .build();

        HttpClient httpClient = HttpClient.newBuilder()
                                        .version(HttpClient.Version.HTTP_2)
                                        .build();
                
        try {
            resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return null;
        }

        // Using Jackson to map json string
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            response = mapper.readValue(resp.body(), NewsList.class);
            logger.info("response: ", response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response;
    }

    public int signup(User user) {
        return 0;
    }
}