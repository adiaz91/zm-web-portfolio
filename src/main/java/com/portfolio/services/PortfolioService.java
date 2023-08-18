package com.portfolio.services;


import com.portfolio.entities.Portfolio;

import java.util.List;

public interface PortfolioService {
    Portfolio findById(Long id);
    Portfolio update(Portfolio portfolio);
    List<Portfolio> findAll();
}
