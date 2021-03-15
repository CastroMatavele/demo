package com.example.demo.repository;

import com.example.demo.model.Cortes;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CorteRepository extends JpaRepository<Cortes, Long> {
}
