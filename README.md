# Literatura Application

## Descripción

La aplicación "Literatura" es un sistema de gestión de libros y autores que permite realizar diversas operaciones relacionadas con la búsqueda, registro y consulta de libros y autores. Utiliza una API externa para obtener información de libros y almacena los datos en una base de datos PostgreSQL.

## Funcionalidades

- **Buscar libro por título**: Busca un libro por su título utilizando una API externa y lo almacena en la base de datos si no existe.
- **Listar libros registrados**: Muestra una lista de todos los libros registrados en la base de datos.
- **Listar autores registrados**: Muestra una lista de todos los autores registrados en la base de datos.
- **Listar autores vivos en un determinado año**: Muestra una lista de autores que estaban vivos en un año específico.
- **Listar libros por idioma**: Muestra una lista de libros registrados filtrados por idioma.
- **Top 10 libros más descargados**: Muestra una lista de los 10 libros más descargados.
- **Buscar autores por nombre**: Busca un autor por su nombre y muestra su información si está registrado en la base de datos.

## Requisitos

- Java 17
- Spring Boot 3.3.0
- PostgreSQL
- Maven o Gradle
