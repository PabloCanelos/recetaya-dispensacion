package com.dispensacion.service;

import com.dispensacion.entity.DetalleDispensacionEntity;
import com.dispensacion.entity.DispensacionEntity;
import com.dispensacion.repository.DispensacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;

@Service
public class DispensacionServiceImpl implements DispensacionService {

    private final DispensacionRepository dispensacionRepository;

    public DispensacionServiceImpl(
            DispensacionRepository dispensacionRepository
    ) {
        this.dispensacionRepository = dispensacionRepository;
    }

    @Override
    public List<DispensacionEntity> listar() {
        return dispensacionRepository.findAll();
    }

    @Override
    public Optional<DispensacionEntity> buscarPorId(Long id) {
        return dispensacionRepository.findById(id);
    }

    @Override
    public List<DispensacionEntity> buscarPorReceta(Long idReceta) {
        return dispensacionRepository.findByIdReceta(idReceta);
    }

    @Override
    public DispensacionEntity crear(
            DispensacionEntity dispensacion
    ) {

        // Registra la fecha automáticamente
        dispensacion.setFechaDispensacion(LocalDateTime.now());

        // Vincula los detalles con la dispensación
        asociarDetalles(dispensacion);

        return dispensacionRepository.save(dispensacion);
    }

    @Override
    public Optional<DispensacionEntity> actualizar(
            Long id,
            DispensacionEntity nuevosDatos
    ) {

        return dispensacionRepository.findById(id)
                .map(dispensacionExistente -> {

                    dispensacionExistente.setIdReceta(
                            nuevosDatos.getIdReceta()
                    );

                    dispensacionExistente.setIdFarmaceutico(
                            nuevosDatos.getIdFarmaceutico()
                    );


                    dispensacionExistente.setEstado(
                            nuevosDatos.getEstado()
                    );

                    dispensacionExistente.getDetalles().clear();

                    if (nuevosDatos.getDetalles() != null) {

                        for (DetalleDispensacionEntity detalle
                                : nuevosDatos.getDetalles()) {

                            detalle.setDispensacion(
                                    dispensacionExistente
                            );

                            dispensacionExistente
                                    .getDetalles()
                                    .add(detalle);
                        }
                    }

                    return dispensacionRepository.save(
                            dispensacionExistente
                    );
                });
    }

    @Override
    public boolean eliminar(Long id) {

        if (!dispensacionRepository.existsById(id)) {
            return false;
        }

        dispensacionRepository.deleteById(id);

        return true;
    }

    private void asociarDetalles(
            DispensacionEntity dispensacion
    ) {

        if (dispensacion.getDetalles() != null) {

            for (DetalleDispensacionEntity detalle
                    : dispensacion.getDetalles()) {

                detalle.setDispensacion(dispensacion);
            }
        }
    }
}