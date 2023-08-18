package com.portfolio.controllers.api;


import com.portfolio.entities.Portfolio;
import com.portfolio.services.PortfolioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/{id}")
    public Portfolio getById(@PathVariable Long id){
        return portfolioService.findById(id);
    }

    @PutMapping
    public Portfolio update(@RequestBody Portfolio portfolio){
        return portfolioService.update(portfolio);
    }


}
