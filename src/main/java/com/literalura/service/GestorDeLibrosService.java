package com.literalura.service;

import com.literalura.entity.Autor;
import com.literalura.entity.Libro;
import com.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestorDeLibrosService {

    private final LibroRepository libroRepository;

    @Autowired
    public GestorDeLibrosService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void guardarDesdeAPI(com.literalura.model.Libro libroApi) {
        if (libroApi.getAuthors().isEmpty()) {
            System.out.println("⚠️ El libro no tiene autor definido.");
            return;
        }

        Autor autor = new Autor(
                libroApi.getAuthors().get(0).getNombre(),
                libroApi.getAuthors().get(0).getAnioNacimiento(),
                libroApi.getAuthors().get(0).getAnioFallecimiento()
        );

        String idioma = libroApi.getLanguages().isEmpty() ? "desconocido" : libroApi.getLanguages().get(0);

        Libro libro = new Libro(
                libroApi.getTitle(),
                idioma,
                libroApi.getNumeroDescargas(),
                autor
        );

        libroRepository.save(libro);
        System.out.println("✅ Guardado en base de datos: " + libro.getTitulo());
    }

    public long contarLibrosPorIdioma(String idioma) {
        return libroRepository.countByIdioma(idioma.toLowerCase());
    }
}