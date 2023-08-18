package com.portfolio.unit.services.impl;

import com.portfolio.data.PortfolioFactory;
import com.portfolio.entities.Portfolio;
import com.portfolio.exceptions.InvalidEntityException;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.repositories.PortfolioRepository;
import com.portfolio.services.impl.PortfolioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceImplTest {

    @InjectMocks
    PortfolioServiceImpl portfolioService;

    @Mock
    PortfolioRepository portfolioRepository;

    Portfolio portfolio;
    Portfolio portfolio2;

    @BeforeEach
    void setUp() {
        portfolio= PortfolioFactory.sample1();
        portfolio2= PortfolioFactory.sample2();
    }

    @Test
    void findById_successful() {


        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(portfolio));

        var result=portfolioService.findById(1L);
        verify(portfolioRepository).findById(1L);

        assertEquals(1L,result.getId());
        assertEquals("mail@mail.com",result.getEmail());
        assertEquals("abc123",result.getAddress());
        assertEquals("summary",result.getExperienceSummary());

    }

    @Test
    void findById_notFound() {

        when(portfolioRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()-> portfolioService.findById(2L) );
        verify(portfolioRepository).findById(2L);

    }

    @Test
    void findAll() {

        var list=List.of(portfolio,portfolio2);
        when(portfolioRepository.findAll()).thenReturn(list);

        var result=portfolioService.findAll();
        verify(portfolioRepository).findAll();

        assertEquals(2,result.size());
        assertEquals(1L,result.get(0).getId());
        assertEquals(2L,result.get(1).getId());

    }

    @Test
    void update() {
        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(portfolio));
        when(portfolioRepository.save(portfolio)).thenReturn(portfolio);

        var result=portfolioService.update(portfolio);
        verify(portfolioRepository).findById(1L);
        verify(portfolioRepository).save(portfolio);

        assertEquals(1L,result.getId());
        assertEquals("mail@mail.com",result.getEmail());
        assertEquals("abc123",result.getAddress());
        assertEquals("summary",result.getExperienceSummary());

    }

    @Test
    void update_not_found() {
        when(portfolioRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,()-> portfolioService.update(portfolio2) );

        verify(portfolioRepository).findById(2L);

    }

    @Test
    void update_no_id() {
        Portfolio p= new Portfolio();
        assertThrows(InvalidEntityException.class,()-> portfolioService.update(p) );

    }
}