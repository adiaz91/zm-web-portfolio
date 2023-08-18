package com.portfolio.repositories;

import com.portfolio.entities.Portfolio;
import org.springframework.data.repository.ListCrudRepository;

public interface PortfolioRepository extends ListCrudRepository<Portfolio,Long> {
}
