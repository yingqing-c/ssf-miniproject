package vttp2022.ssfminiprojectfinance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vttp2022.ssfminiprojectfinance.SsfminiprojectFinanceApplicationTests.newsResponseJsonString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import vttp2022.ssfminiprojectfinance.models.News;
import vttp2022.ssfminiprojectfinance.services.FinanceAPIService;

@RunWith(MockitoJUnitRunner.class)
public class TestFinanceAPIService {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FinanceAPIService financeAPIService;

    @Test
    public void testGetNews() {
        ResponseEntity<String> resp = new ResponseEntity(newsResponseJsonString, HttpStatus.ACCEPTED);

        Mockito.when(restTemplate.exchange(
            ArgumentMatchers.eq(FinanceAPIService.URL+"news/v2/get-details"),
            ArgumentMatchers.eq(HttpMethod.GET),
            ArgumentMatchers.any(HttpEntity.class),
            ArgumentMatchers.<Class<String>>any(),
            ArgumentMatchers.anyInt()
        )).thenReturn(resp);

        News news = financeAPIService.getNews("uuid");

		assertEquals(news.getTitle(),"Dow Jones Futures Fall: How To Handle The Stock Market Rally As Tesla, Big Techs Tumble");


    }    
}
