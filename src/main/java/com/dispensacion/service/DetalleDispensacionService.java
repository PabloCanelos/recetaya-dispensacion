package com.dispensacion.service;

import com.dispensacion.entity.DetalleDispensacionEntity;

import java.util.List;
import java.util.Optional;

public interface DetalleDispensacionService {

    // Lista todos los detalles
    List<DetalleDispensacionEntity> listar();

    // Busca un detalle por ID
    Optional<DetalleDispensacionEntity> buscarPorId(Long id);

    // Busca detalles de una dispensación
    List<DetalleDispensacionEntity> buscarPorDispensacion(Long idDispensacion);

    // Crea un nuevo detalle
    DetalleDispensacionEntity crear(DetalleDispensacionEntity detalle);

    // Actualiza un detalle existente
    Optional<DetalleDispensacionEntity> actualizar(
            Long id,
            DetalleDispensacionEntity detalle
    );

    // Elimina un detalle
    boolean eliminar(Long id);
}