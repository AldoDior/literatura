package com.alura.literatura.main;

import com.alura.literatura.model.*;
import com.alura.literatura.repository.AuthorRepository;
import com.alura.literatura.repository.BookRepository;
import com.alura.literatura.util.ConvertData;
import com.alura.literatura.util.FetchAPI;


import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner teclado = new Scanner(System.in);
    private FetchAPI fetchAPI = new FetchAPI();
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvertData convertidor = new ConvertData();
    private BookRepository libroRepo;
    private AuthorRepository autorRepo;

    public Main(BookRepository libroRepo, AuthorRepository autorRepo) {
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("------------");
            System.out.println("Elija la opción a través de su número");
            System.out.println("""
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores registrados vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Top 10 libros mas descargados
                    7 - Buscar autores por nombre
                    0 - Salir
                    """);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosPorAnio();
                case 5 -> listarLibrosPorIdioma();
                case 6 -> listarTop10LibrosMasDescargados();
                case 7 -> buscarAutorPorNombre();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private Data buscarLibro() {
        System.out.println("Ingrese el nombre del libro que quiere buscar: ");
        String titulo = teclado.nextLine();
        String json = fetchAPI.obtenerDatos(URL_BASE + titulo.toLowerCase().replace(" ", "+"));
        return convertidor.obtenerDatos(json, Data.class);
    }

    private void buscarLibroPorTitulo() {
        Data datos = buscarLibro();
        if (!datos.results().isEmpty()) {
            DataBook libroData = datos.results().get(0);
            DataAuthor autorData = libroData.author().get(0);
            System.out.println("""
                    El libro encontrado y guardado es: 
                    Nombre: %s
                    Autor: %s
                    Idioma: %s
                    """.formatted(libroData.title(), autorData.name(), libroData.languages()));
            Book libroEncontrado = libroRepo.findByTitleContainingIgnoreCase(libroData.title());

            if (libroEncontrado != null) {
                System.out.println("El libro ya existe");
            } else {
                Author autorEncontrado = autorRepo.findByNameIgnoreCase(autorData.name());
                if (autorEncontrado == null) {
                    Author autor = new Author(autorData);
                    autorRepo.save(autor);
                    Book libro = new Book(libroData, autor);
                    libroRepo.save(libro);
                } else {
                    Book libro = new Book(libroData, autorEncontrado);
                    libroRepo.save(libro);
                }
            }
        } else {
            System.out.println("No se encuentra...");
        }
    }

    private void listarLibrosRegistrados() {
        List<Book> libros = libroRepo.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Author> autores = autorRepo.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosPorAnio() {
        System.out.println("Ingrese el año vivo de autor(es) que quiere buscar: ");
        int anio = teclado.nextInt();
        List<Author> autores = autorRepo.findAutoresVivosPorAnio(anio);
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarLibrosPorIdioma() {
        List<String> idiomas = List.of("es", "en", "it", "hu", "tl");
        System.out.println("""
                Escriba el idioma en el que desea hacer la búsqueda
                Los idiomas disponibles son: es, en, it, hu, tl
                """);
        String idioma = teclado.nextLine();
        if (!idiomas.contains(idioma.toLowerCase())) {
            System.out.println("No disponible");
        } else {
            libroRepo.findByLanguages(idioma).forEach(System.out::println);
        }
    }

    private void listarTop10LibrosMasDescargados() {
        libroRepo.findTop10ByOrderByDownloadsAmountDesc().forEach(System.out::println);
    }

    private void buscarAutorPorNombre() {
        System.out.println("Ingrese el nombre del autor: ");
        String nombreAutor = teclado.nextLine();
        Author autor = autorRepo.findByNameIgnoreCase(nombreAutor);
        if (autor != null) {
            System.out.println(autor);
        } else {
            System.out.println("Autor no encontrado");
        }
    }
}

