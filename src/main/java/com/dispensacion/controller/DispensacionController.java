package com.dispensacion.controller;

import com.dispensacion.dto.DetalleDispensacionRequestDTO;
import com.dispensacion.dto.DispensacionRequestDTO;
import com.dispensacion.entity.DetalleDispensacionEntity;
import com.dispensacion.entity.DispensacionEntity;
import com.dispensacion.service.DispensacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // Expone endpoints REST
@RequestMapping("/api/dispensaciones")
public class DispensacionController {

    private final DispensacionService dispensacionService;

    // Inyección del servicio
    public DispensacionController(DispensacionService dispensacionService) {
        this.dispensacionService = dispensacionService;
    }

    // GET: lista todas las dispensaciones
    @GetMapping
    public ResponseEntity<List<DispensacionEntity>> listar() {

        return ResponseEntity.ok(
                dispensacionService.listar()
        );
    }

    // GET: busca una dispensación por ID
    @GetMapping("/{id}")
    public ResponseEntity<DispensacionEntity> buscarPorId(
            @PathVariable Long id
    ) {

        return dispensacionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    // GET: busca dispensaciones asociadas a una receta
    @GetMapping("/receta/{idReceta}")
    public ResponseEntity<List<DispensacionEntity>> buscarPorReceta(
            @PathVariable Long idReceta
    ) {

        return ResponseEntity.ok(
                dispensacionService.buscarPorReceta(idReceta)
        );
    }

    // POST: registra una nueva dispensación
    @PostMapping
    public ResponseEntity<DispensacionEntity> crear(
            @RequestBody DispensacionRequestDTO dto
    ) {

        // Convierte los datos recibidos a Entity
        DispensacionEntity dispensacion = convertirDTO(dto);

        // Guarda la dispensación
        DispensacionEntity creada =
                dispensacionService.crear(dispensacion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creada);
    }

    // PUT: reemplaza los datos de una dispensación
    @PutMapping("/{id}")
    public ResponseEntity<DispensacionEntity> actualizar(
            @PathVariable Long id,
            @RequestBody DispensacionRequestDTO dto
    ) {

        // Convierte el DTO antes de actualizar
        DispensacionEntity nuevosDatos = convertirDTO(dto);

        return dispensacionService.actualizar(id, nuevosDatos)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    // DELETE: elimina una dispensación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {

        boolean eliminado =
                dispensacionService.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Convierte el DTO recibido a una Entity
    private DispensacionEntity convertirDTO(
            DispensacionRequestDTO dto
    ) {

        DispensacionEntity dispensacion =
                new DispensacionEntity();

        dispensacion.setIdReceta(dto.getIdReceta());
        dispensacion.setIdFarmaceutico(dto.getIdFarmaceutico());
        dispensacion.setEstado(dto.getEstado());

        List<DetalleDispensacionEntity> detalles =
                new ArrayList<>();

        if (dto.getDetalles() != null) {

            for (DetalleDispensacionRequestDTO detalleDTO
                    : dto.getDetalles()) {

                DetalleDispensacionEntity detalle =
                        new DetalleDispensacionEntity();

                detalle.setIdMedicamento(
                        detalleDTO.getIdMedicamento()
                );

                detalle.setCantidad(
                        detalleDTO.getCantidad()
                );

                detalles.add(detalle);
            }
        }

        dispensacion.setDetalles(detalles);

        return dispensacion;
    }
}