package com.dispensacion.repository;

import com.dispensacion.entity.DispensacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DispensacionRepository
        extends JpaRepository<DispensacionEntity, Long> {

    List<DispensacionEntity> findByIdReceta(Long idReceta);
}