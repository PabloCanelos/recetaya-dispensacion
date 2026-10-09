package com.dispensacion.service;

import com.dispensacion.entity.DetalleDispensacionEntity;
import com.dispensacion.repository.DetalleDispensacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Lógica de negocio de los detalles
public class DetalleDispensacionServiceImpl
        implements DetalleDispensacionService {

    // Acceso a la base de datos
    private final DetalleDispensacionRepository detalleRepository;

    // Inyección de dependencia por constructor
    public DetalleDispensacionServiceImpl(
            DetalleDispensacionRepository detalleRepository
    ) {
        this.detalleRepository = detalleRepository;
    }

    @Override
    public List<DetalleDispensacionEntity> listar() {
        return detalleRepository.findAll(); // Obtiene todos
    }

    @Override
    public Optional<DetalleDispensacionEntity> buscarPorId(Long id) {
        return detalleRepository.findById(id); // Busca por PK
    }

    @Override
    public List<DetalleDispensacionEntity> buscarPorDispensacion(
            Long idDispensacion
    ) {
        // Filtra por dispensación
        return detalleRepository
                .findByDispensacionIdDispensacion(idDispensacion);
    }

    @Override
    public DetalleDispensacionEntity crear(
            DetalleDispensacionEntity detalle
    ) {
        return detalleRepository.save(detalle); // Inserta registro
    }

    @Override
    public Optional<DetalleDispensacionEntity> actualizar(
            Long id,
            DetalleDispensacionEntity nuevosDatos
    ) {

        // Verifica que el detalle exista
        return detalleRepository.findById(id)
                .map(detalleExistente -> {

                    detalleExistente.setIdMedicamento(
                            nuevosDatos.getIdMedicamento()
                    );

                    detalleExistente.setCantidad(
                            nuevosDatos.getCantidad()
                    );

                    detalleExistente.setDispensacion(
                            nuevosDatos.getDispensacion()
                    );

                    // Guarda los cambios
                    return detalleRepository.save(detalleExistente);
                });
    }

    @Override
    public boolean eliminar(Long id) {

        // Devuelve false si no existe
        if (!detalleRepository.existsById(id)) {
            return false;
        }

        detalleRepository.deleteById(id); // Elimina registro
        return true;
    }
}