package com.digitalworlds.api1.repository;

import com.digitalworlds.api1.dto.ClimaDTO;
import com.digitalworlds.api1.entities.ClimaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IClimaRepository extends JpaRepository <ClimaEntity, Long> {
}
