package com.alura.literatura.repository;

import com.alura.literatura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByNameIgnoreCase(String name);

    @Query("SELECT a FROM Author a WHERE :anio BETWEEN a.anioNacimiento AND a.anioFallecimiento")
    List<Author> findAutoresVivosPorAnio(int anio);
}