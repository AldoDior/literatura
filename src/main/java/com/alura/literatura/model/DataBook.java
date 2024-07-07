package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<DataAuthor> author,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") double downloadsAmount
) {}
