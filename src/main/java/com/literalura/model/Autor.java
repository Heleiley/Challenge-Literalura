package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private Integer anioNacimiento;

    @JsonAlias("death_year")
    private Integer anioFallecimiento;

    public String getNombre() {
        return nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    @Override
    public String toString() {
        return nombre + " (" + anioNacimiento + " - " + (anioFallecimiento != null ? anioFallecimiento : "vivo") + ")";
    }
}