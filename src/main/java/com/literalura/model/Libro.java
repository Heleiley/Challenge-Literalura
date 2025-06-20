package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {

    private String title;
    private List<Autor> authors;
    private List<String> languages;

    @JsonAlias("download_count")
    private int numeroDescargas;

    public String getTitle() {
        return title;
    }

    public List<Autor> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    @Override
    public String toString() {
        return "📖 " + title + " | Autor: " + authors.get(0) +
                " | Idioma: " + (languages.isEmpty() ? "desconocido" : languages.get(0)) +
                " | Descargas: " + numeroDescargas;
    }
}