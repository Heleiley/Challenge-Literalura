package com.literalura;

import com.literalura.entity.Libro;
import com.literalura.service.APIService;
import com.literalura.service.Conversor;
import com.literalura.service.GestorDeLibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Principal implements CommandLineRunner {

    @Autowired
    private GestorDeLibrosService gestorDeLibros;

    private final APIService apiService = new APIService();
    private final Conversor conversor = new Conversor();
    private final Scanner scanner = new Scanner(System.in);
    private final List<com.literalura.model.Libro> librosBuscados = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }

    @Override
    public void run(String... args) {
        int opcion;
        do {
            mostrarMenu();
            String input = scanner.nextLine();
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Por favor, ingrese un número.");
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibrosBuscados();
                case 3 -> filtrarLibrosPorIdioma();
                case 4 -> guardarUltimoLibroBuscado();
                case 5 -> mostrarCantidadPorIdioma();
                case 0 -> System.out.println("👋 ¡Hasta la próxima!");
                default -> System.out.println("⚠️ Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n📚 === MENÚ LITERALURA ===");
        System.out.println("1. Buscar libro por título");
        System.out.println("2. Listar todos los libros buscados");
        System.out.println("3. Filtrar libros por idioma");
        System.out.println("4. Guardar último libro buscado en la base de datos");
        System.out.println("5. Contar cantidad de libros por idioma");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void buscarLibroPorTitulo() {
        System.out.print("🔎 Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        String json = apiService.buscarLibro(titulo);
        List<com.literalura.model.Libro> resultados = conversor.obtenerLibros(json);

        if (resultados.isEmpty()) {
            System.out.println("⚠️ No se encontró ningún resultado.");
        } else {
            com.literalura.model.Libro libro = resultados.get(0);
            librosBuscados.add(libro);
            System.out.println("\n📖 Libro encontrado:");
            System.out.println(libro);
        }
    }

    private void listarLibrosBuscados() {
        if (librosBuscados.isEmpty()) {
            System.out.println("📂 No se han buscado libros aún.");
        } else {
            System.out.println("\n🗃️ Libros buscados:");
            librosBuscados.forEach(System.out::println);
        }
    }

    private void filtrarLibrosPorIdioma() {
        if (librosBuscados.isEmpty()) {
            System.out.println("❌ No hay libros buscados para filtrar.");
            return;
        }

        System.out.print("🌐 Ingrese el código de idioma (ej: en, es, fr): ");
        String idioma = scanner.nextLine();

        var filtrados = librosBuscados.stream()
                .filter(libro -> !libro.getLanguages().isEmpty()
                        && libro.getLanguages().get(0).equalsIgnoreCase(idioma))
                .toList();

        if (filtrados.isEmpty()) {
            System.out.println("🙁 No se encontraron libros en ese idioma.");
        } else {
            System.out.println("\n✅ Libros en idioma '" + idioma + "':");
            filtrados.forEach(System.out::println);
        }
    }

    private void guardarUltimoLibroBuscado() {
        if (librosBuscados.isEmpty()) {
            System.out.println("⚠️ No hay libros buscados para guardar.");
            return;
        }

        com.literalura.model.Libro ultimo = librosBuscados.get(librosBuscados.size() - 1);
        gestorDeLibros.guardarDesdeAPI(ultimo);
    }

    private void mostrarCantidadPorIdioma() {
        System.out.print("🌐 Ingrese el idioma a consultar (ej: en, es, fr): ");
        String idioma = scanner.nextLine();

        long cantidad = gestorDeLibros.contarLibrosPorIdioma(idioma);
        System.out.println("📘 Cantidad de libros en idioma '" + idioma + "': " + cantidad);
    }
}