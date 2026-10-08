package com.dispensacion.repository;

import com.dispensacion.entity.DetalleDispensacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleDispensacionRepository
        extends JpaRepository<DetalleDispensacionEntity, Long> {

    List<DetalleDispensacionEntity>
    findByDispensacionIdDispensacion(Long idDispensacion);
}