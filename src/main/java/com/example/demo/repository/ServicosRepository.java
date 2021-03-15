package com.example.demo.repository;

import com.example.demo.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicosRepository extends JpaRepository<Servico,Long> {
}
