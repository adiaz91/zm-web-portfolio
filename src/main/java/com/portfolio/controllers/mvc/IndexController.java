package com.portfolio.controllers.mvc;


import com.portfolio.services.PortfolioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

@Controller
public class IndexController {

    private final PortfolioService portfolioService;


    public IndexController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public String index(Map<String, Object> model) {
        model.put("list", portfolioService.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String portfolio(@PathVariable Long id, Map<String, Object> model)  {
        var portfolio = portfolioService.findById(id);
        model.put("portfolio", portfolio);
        return "portfolio";
    }
}
