package com.dispensacion.service;

import com.dispensacion.entity.DetalleDispensacionEntity;
import com.dispensacion.repository.DetalleDispensacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Lógica de negocio para consultas de detalles
public class DetalleDispensacionServiceImpl
        implements DetalleDispensacionService {

    private final DetalleDispensacionRepository detalleRepository;

    // Inyección de dependencia por constructor
    public DetalleDispensacionServiceImpl(
            DetalleDispensacionRepository detalleRepository
    ) {
        this.detalleRepository = detalleRepository;
    }

    @Override
    public List<DetalleDispensacionEntity> listar() {
        return detalleRepository.findAll();
    }

    @Override
    public Optional<DetalleDispensacionEntity> buscarPorId(Long id) {
        return detalleRepository.findById(id);
    }

    @Override
    public List<DetalleDispensacionEntity> buscarPorDispensacion(
            Long idDispensacion
    ) {
        return detalleRepository
                .findByDispensacionIdDispensacion(idDispensacion);
    }
}