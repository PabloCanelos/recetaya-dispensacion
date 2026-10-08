package com.dispensacion.controller;

import com.dispensacion.entity.DetalleDispensacionEntity;
import com.dispensacion.service.DetalleDispensacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Endpoints REST de consulta
@RequestMapping("/api/detalles-dispensacion")
public class DetalleDispensacionController {

    private final DetalleDispensacionService detalleService;

    // Inyección del servicio
    public DetalleDispensacionController(
            DetalleDispensacionService detalleService
    ) {
        this.detalleService = detalleService;
    }

    // GET: lista todos los detalles
    @GetMapping
    public ResponseEntity<List<DetalleDispensacionEntity>> listar() {

        return ResponseEntity.ok(
                detalleService.listar()
        );
    }

    // GET: busca un detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleDispensacionEntity> buscarPorId(
            @PathVariable Long id
    ) {

        return detalleService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    // GET: detalles asociados a una dispensación
    @GetMapping("/dispensacion/{idDispensacion}")
    public ResponseEntity<List<DetalleDispensacionEntity>>
    buscarPorDispensacion(
            @PathVariable Long idDispensacion
    ) {

        return ResponseEntity.ok(
                detalleService.buscarPorDispensacion(idDispensacion)
        );
    }
}