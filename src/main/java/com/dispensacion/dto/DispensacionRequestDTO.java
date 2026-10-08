package com.dispensacion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DispensacionRequestDTO {

    private Long idReceta; // Receta asociada

    private Long idFarmaceutico; // Usuario que entrega

    private String estado; // Estado propio del registro

    private List<DetalleDispensacionRequestDTO> detalles; // Medicamentos entregados
}