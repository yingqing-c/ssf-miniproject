package vttp2022.ssfminiprojectfinance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.ssfminiprojectfinance.models.News;
import vttp2022.ssfminiprojectfinance.models.NewsList;
import vttp2022.ssfminiprojectfinance.models.User;
import vttp2022.ssfminiprojectfinance.services.FinanceAPIService;

@Controller
@RequestMapping
public class FinanceController {
    
    @Autowired
    private FinanceAPIService financeAPIService;
    
    @GetMapping (path = {"/index" })
	public String showIndexPage(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
		return "index";
	}

    @GetMapping(path = "/news/get-details")
    public String getNews(@RequestParam String name, @RequestParam String uuid, Model model) {
        model.addAttribute("name", name);
        News result = financeAPIService.getNews(uuid);
        model.addAttribute("result", result);
        financeAPIService.updateHistory(name, uuid, result.getTitle());
        return "news";
    }

    @GetMapping(path = "/newslist")
    public String newsList(@RequestParam String name, Model model) {
        model.addAttribute("name", name); 
        NewsList newsList = financeAPIService.getNewsList();
        model.addAttribute("newslist", newsList);
        return "newslist";
    }

    @GetMapping(path="/history")
    public String getHistory(@RequestParam String name, Model model) {
        model.addAttribute("name", name); 
        User user = financeAPIService.getUser(name);
        model.addAttribute("titles", user.getTitles());
        model.addAttribute("history", user.getHistory());
        return "history";
    }

}

