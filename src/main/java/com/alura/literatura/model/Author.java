package com.alura.literatura.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int anioNacimiento;
    private int anioFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> libros;

    public Author(DataAuthor dataAuthor) {
        this.nombre = dataAuthor.name();
        this.anioNacimiento = dataAuthor.birthYear();
        this.anioFallecimiento = dataAuthor.deathYear();
    }

    public Author() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public int getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(int anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public List<Book> getLibros() {
        return libros;
    }

    public void setLibros(List<Book> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return """
                Autor: %s
                Fecha de nacimiento: %s
                Fecha de fallecimiento: %s
                Libros: %s
                """.formatted(nombre, anioNacimiento, anioFallecimiento, libros.stream().map(Book::getTitulo).collect(Collectors.toList()));
    }
}


