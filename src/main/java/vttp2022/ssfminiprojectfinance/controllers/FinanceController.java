package vttp2022.ssfminiprojectfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.ssfminiprojectfinance.models.News;
import vttp2022.ssfminiprojectfinance.models.NewsList;
import vttp2022.ssfminiprojectfinance.services.FinanceAPIService;

@Controller
@RequestMapping
public class FinanceController {
    
    @Autowired
    private FinanceAPIService financeAPIService;
    
    @GetMapping("/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
        
		return model;
	}
    
    @GetMapping(path = "/news/v2/get-details/{uuid}")
    public String getNews(@PathVariable String uuid, Model model) {

        News result = financeAPIService.getNews(uuid);
        model.addAttribute("result", result);

        System.out.println(result);

        return "news";
    }

    @PostMapping(path = "/news/v2/list")
    public String newsList(Model model) {
        
        NewsList newsList = financeAPIService.getNewsList();
        System.out.println(newsList);
        // System.out.println(newsList.getNewsList());
        model.addAttribute("result", newsList);
        
        return "newslist";
    }

}

