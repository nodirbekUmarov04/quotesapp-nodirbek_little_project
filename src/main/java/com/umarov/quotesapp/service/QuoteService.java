package com.umarov.quotesapp.service;

import com.umarov.quotesapp.entity.Quote;
import com.umarov.quotesapp.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }


    public Quote getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        if (!quotes.isEmpty()) {
            return quotes.get((int) (Math.random() * quotes.size()));
        }
        return null;
    }

    public Quote addQuote(Quote quote) {
        return quoteRepository.save(quote);
    }
}