package com.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.model.DatosApi;
import com.literalura.model.Libro;

import java.util.List;

public class Conversor {

    public List<Libro> obtenerLibros(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DatosApi datos = mapper.readValue(json, DatosApi.class);
            return datos.getResults();
        } catch (Exception e) {
            System.out.println("Error al convertir JSON: " + e.getMessage());
            return List.of();
        }
    }
}