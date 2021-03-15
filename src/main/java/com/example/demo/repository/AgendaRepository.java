package com.example.demo.repository;

import com.example.demo.model.Agenda;
import com.example.demo.model.DiasNaoUteis;
import com.example.demo.model.HorasInvalidas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Time;
import java.util.Date;
import java.util.List;
public interface AgendaRepository extends JpaRepository<Agenda,Long> {

}
