package com.dispensacion.service;

import com.dispensacion.entity.DispensacionEntity;

import java.util.List;
import java.util.Optional;

public interface DispensacionService {

    List<DispensacionEntity> listar();

    Optional<DispensacionEntity> buscarPorId(Long id);

    List<DispensacionEntity> buscarPorReceta(Long idReceta);

    DispensacionEntity crear(DispensacionEntity dispensacion);

    Optional<DispensacionEntity> actualizar(
            Long id,
            DispensacionEntity dispensacion
    );

    boolean eliminar(Long id);
}