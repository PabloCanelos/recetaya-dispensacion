package com.dispensacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_dispensaciones")
@Getter
@Setter
@NoArgsConstructor
public class DetalleDispensacionEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id_detalle_dispensacion")
     private Long idDetalleDispensacion;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "id_dispensacion", nullable = false)
     private DispensacionEntity dispensacion;

     @Column(name = "id_medicamento", nullable = false)
     private Long idMedicamento;

     @Column(name = "cantidad", nullable = false)
     private Integer cantidad;
}