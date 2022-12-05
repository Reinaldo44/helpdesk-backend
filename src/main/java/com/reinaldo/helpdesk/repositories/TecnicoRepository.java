package com.reinaldo.helpdesk.repositories;

import com.reinaldo.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {


}
