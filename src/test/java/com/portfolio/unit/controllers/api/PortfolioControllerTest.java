package com.portfolio.unit.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.controllers.api.PortfolioController;
import com.portfolio.data.PortfolioFactory;
import com.portfolio.entities.Portfolio;
import com.portfolio.exceptions.InvalidEntityException;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.services.PortfolioService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PortfolioController.class)
class PortfolioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PortfolioService portfolioService;

    @Autowired
    private ObjectMapper objectMapper;
    private static String PATH="/api";

    Portfolio portfolio;


    @BeforeEach
    void setUp() {
        portfolio= PortfolioFactory.sample1();

    }

    @Test
    void findById_successful() throws Exception {

        final long ID=1L;
        when(portfolioService.findById(ID)).thenReturn(portfolio);

        this.mockMvc.perform(get(PATH+ "/{id}",ID)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.email", Is.is("mail@mail.com")))
                .andExpect(jsonPath("$.address", Is.is("abc123")))
                .andExpect(jsonPath("$.experienceSummary", Is.is("summary"))
               );
        verify(portfolioService).findById(ID);

    }

    @Test
    void findById_notFound() throws Exception {

        final long ID=10L;
        when(portfolioService.findById(ID)).thenThrow(new ResourceNotFoundException("Not found"));
        this.mockMvc.perform(get(PATH+ "/{id}",ID)).andExpect(status().isNotFound());
        verify(portfolioService).findById(ID);

    }



    @Test
    void update_successful() throws Exception {
        when(portfolioService.update(any(Portfolio.class))).thenReturn(portfolio);

        this.mockMvc.perform(put(PATH).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(portfolio))).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.email", Is.is("mail@mail.com")))
                .andExpect(jsonPath("$.address", Is.is("abc123")))
                .andExpect(jsonPath("$.experienceSummary", Is.is("summary"))
                );
        verify(portfolioService).update(any(Portfolio.class));

    }

    @Test
    void update_not_found() throws Exception {
        when(portfolioService.update(any(Portfolio.class))).thenThrow(new ResourceNotFoundException("Not found"));
        this.mockMvc.perform(put(PATH).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(portfolio))).andExpect(status().isNotFound());
        verify(portfolioService).update(any(Portfolio.class));

    }

    @Test
    void update_bad_request() throws Exception {
        when(portfolioService.update(any(Portfolio.class))).thenThrow(new InvalidEntityException("Invalid"));
        this.mockMvc.perform(put(PATH).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(portfolio))).andExpect(status().isBadRequest());
        verify(portfolioService).update(any(Portfolio.class));

    }
}