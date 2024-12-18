package com.umarov.quotesapp.repository;

import com.umarov.quotesapp.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}