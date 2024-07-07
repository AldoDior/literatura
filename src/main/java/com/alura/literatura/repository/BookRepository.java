package com.alura.literatura.repository;

import com.alura.literatura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleContainingIgnoreCase(String titulo);

    @Query("SELECT b FROM Book b WHERE b.idioma = :idioma")
    List<Book> findByLanguages(String idioma);

    @Query("SELECT b FROM Book b ORDER BY b.cantidadDescargas DESC")
    List<Book> findTop10ByOrderByDownloadsAmountDesc();
}


