package com.umarov.quotesapp.service;

import com.umarov.quotesapp.model.Quote;
import com.umarov.quotesapp.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

public class QuoteServiceTest {

    @Mock
    private QuoteRepository quoteRepository;

    private QuoteService quoteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quoteService = new QuoteService(quoteRepository);
    }

    @Test
    void addQuoteTest() {
        Quote newQuote = new Quote(null, "This is a quote", "Author");
        Quote savedQuote = new Quote(1L, "This is a quote", "Author");

        when(quoteRepository.save(newQuote)).thenReturn(savedQuote);

        Quote result = quoteService.addQuote(newQuote);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Author", result.getAuthor());
        assertEquals("This is a quote", result.getText());
    }

    @Test
    void getAllQuotesTest() {
        List<Quote> quotes = List.of(
                new Quote(1L, "Quote 1", "Author 1"),
                new Quote(2L, "Quote 2", "Author 2")
        );

        when(quoteRepository.findAll()).thenReturn(quotes);

        List<Quote> result = quoteService.getAllQuotes();

        assertEquals(2, result.size());
    }

    @Test
    void updateQuoteTest() {
        Quote existingQuote = new Quote(1L, "Old Text", "Old Author");
        Quote updatedQuote = new Quote(1L, "New Text", "New Author");

        when(quoteRepository.findById(1L)).thenReturn(Optional.of(existingQuote));
        when(quoteRepository.save(updatedQuote)).thenReturn(updatedQuote);

        Quote result = quoteService.updateQuote(1L, updatedQuote);

        assertEquals("New Author", result.getAuthor());
        assertEquals("New Text", result.getText());
    }

    @Test
    void deleteQuoteTest() {
        Quote existingQuote = new Quote(1L, "Text", "Author");

        when(quoteRepository.existsById(1L)).thenReturn(true);

        when(quoteRepository.findById(1L)).thenReturn(Optional.of(existingQuote));

        quoteService.deleteQuote(1L);

        verify(quoteRepository, times(1)).deleteById(1L);
    }

}