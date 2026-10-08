package com.dispensacion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetalleDispensacionRequestDTO {

    private Long idMedicamento; // Medicamento entregado

    private Integer cantidad; // Cantidad dispensada
}