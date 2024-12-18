package com.umarov.quotesapp.controller;

import com.umarov.quotesapp.model.Quote;
import com.umarov.quotesapp.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public List<Quote> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @GetMapping("/random")
    public Quote getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @PostMapping
    public Quote addQuote(@RequestBody Quote quote) {
        return quoteService.addQuote(quote);
    }
}