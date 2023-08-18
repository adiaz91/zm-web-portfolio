package com.portfolio.services.impl;


import com.portfolio.entities.Portfolio;
import com.portfolio.exceptions.InvalidEntityException;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.repositories.PortfolioRepository;
import com.portfolio.services.PortfolioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Portfolio findById(Long id) {
        return portfolioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Portfolio {" + id + "} not found")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Portfolio> findAll() {
        return portfolioRepository.findAll();
    }

    @Override
    @Transactional
    public Portfolio update(Portfolio portfolio) {
        if(portfolio==null || portfolio.getId()==null){
            throw new InvalidEntityException("The provided entity ID is invalid or missing");
        }
        findById(portfolio.getId());
        return portfolioRepository.save(portfolio);
    }


}
