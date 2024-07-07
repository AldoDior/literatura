package com.alura.literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String titulo;

    private String idioma;

    private double cantidadDescargas;

    @ManyToOne
    private Author autor;

    public Book() {}

    public Book(DataBook dataBook, Author autor) {
        this.titulo = dataBook.title();
        this.autor = autor;
        this.idioma = dataBook.languages().get(0);
        this.cantidadDescargas = dataBook.downloadsAmount();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Author getAutor() {
        return autor;
    }

    public void setAutor(Author autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public double getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(double cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return """
                ------ LIBRO ------
                Título: %s
                Autor: %s
                Idioma: %s 
                Número de descargas: %s
                """.formatted(titulo, autor.getNombre(), idioma, cantidadDescargas);
    }
}

