package com.umarov.quotesapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @JsonProperty("author")
    private String author;

    public Quote() {
    }
    //    public Quote(Long id, String author, String text) {
//        this.id = id;
//        this.author = author;
//        this.text = text;
//    }

}