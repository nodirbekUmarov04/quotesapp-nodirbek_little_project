package com.umarov.quotesapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umarov.quotesapp.model.Quote;
import com.umarov.quotesapp.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class QuoteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuoteService quoteService;

    @InjectMocks
    private QuoteController quoteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(quoteController).build();
    }

    @Test
    void getAllQuotesTest() throws Exception {
        mockMvc.perform(get("/quotes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addQuoteTest() throws Exception {
        Quote quote = new Quote(1L, "This is a quote", "Author");

        when(quoteService.addQuote(any(Quote.class))).thenReturn(quote);

        mockMvc.perform(post("/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"author\": \"Author\", \"text\": \"This is a quote\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.author").value("Author"))
                .andExpect(jsonPath("$.text").value("This is a quote"));
    }

    @Test
    void updateQuoteTest() throws Exception {
        Quote updatedQuote = new Quote(1L, "New Text", "New Author");

        when(quoteService.updateQuote(eq(1L), any(Quote.class))).thenReturn(updatedQuote);

        mockMvc.perform(put("/quotes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"author\": \"New Author\", \"text\": \"New Text\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("New Author"))
                .andExpect(jsonPath("$.text").value("New Text"));
    }

    @Test
    void deleteQuoteTest() throws Exception {
        Quote quote = new Quote(1L, "This is a quote", "Author");
        quoteService.addQuote(quote);

        doNothing().when(quoteService).deleteQuote(1L);

        mockMvc.perform(delete("/quotes/1"))
                .andExpect(status().isNoContent());


        verify(quoteService, times(1)).deleteQuote(1L);
    }



}
