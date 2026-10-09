package com.dispensacion.service;

import com.dispensacion.entity.DetalleDispensacionEntity;

import java.util.List;
import java.util.Optional;

public interface DetalleDispensacionService {

    // Lista todos los detalles
    List<DetalleDispensacionEntity> listar();

    // Busca un detalle por ID
    Optional<DetalleDispensacionEntity> buscarPorId(Long id);

    // Busca los detalles de una dispensación
    List<DetalleDispensacionEntity> buscarPorDispensacion(Long idDispensacion);
}