package com.portfolio.unit.controllers.mvc;

import com.portfolio.controllers.mvc.IndexController;
import com.portfolio.data.PortfolioFactory;
import com.portfolio.entities.Portfolio;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.services.PortfolioService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndexController.class)
class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PortfolioService portfolioService;

    private static String PATH="";

    Portfolio portfolio;


    @BeforeEach
    void setUp() {
        portfolio= PortfolioFactory.sample1();

    }

    @Test
    void findById_successful() throws Exception {

        final long ID=1L;
        when(portfolioService.findById(ID)).thenReturn(portfolio);
        this.mockMvc.perform(get(PATH+ "/{id}",ID)).andExpect(status().isOk());
        verify(portfolioService).findById(ID);

    }

    @Test
    void findById_notFound() throws Exception {

        final long ID=10L;
        when(portfolioService.findById(ID)).thenThrow(new ResourceNotFoundException("Not found"));
        this.mockMvc.perform(get(PATH+ "/{id}",ID)).andExpect(status().isNotFound());
        verify(portfolioService).findById(ID);

    }
}