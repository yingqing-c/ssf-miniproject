package vttp2022.ssfminiprojectfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.ssfminiprojectfinance.models.News;
import vttp2022.ssfminiprojectfinance.services.FinanceAPIService;

@RestController
@RequestMapping
public class FinanceRestController {
    
    @Autowired
    private FinanceAPIService financeAPIService;
    
    @GetMapping(path="/api/news/get-details/{uuid}")
    public ResponseEntity<News> getNews(@PathVariable String uuid) {
        News result = financeAPIService.getNews(uuid);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(result);
    }
}
