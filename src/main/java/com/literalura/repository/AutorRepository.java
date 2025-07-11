package com.literalura.repository;

import com.literalura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByAnioFallecimientoIsNullOrAnioFallecimientoGreaterThan(int anio);
}