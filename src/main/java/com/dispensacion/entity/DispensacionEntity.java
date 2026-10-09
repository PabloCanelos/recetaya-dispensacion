package com.dispensacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "dispensaciones")
@Getter
@Setter
@NoArgsConstructor
public class DispensacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dispensacion")
    private Long idDispensacion;

    @Column(name = "id_receta", nullable = false)
    private Long idReceta;

    @Column(name = "id_farmaceutico", nullable = false)
    private Long idFarmaceutico;

    @Column(name = "fecha_dispensacion", nullable = false)
    private LocalDateTime fechaDispensacion;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;


    @JsonManagedReference // Incluye detalles en el JSON
    @OneToMany(
            mappedBy = "dispensacion",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<DetalleDispensacionEntity> detalles = new ArrayList<>();
}