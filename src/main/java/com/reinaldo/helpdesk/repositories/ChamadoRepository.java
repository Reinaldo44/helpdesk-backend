package com.reinaldo.helpdesk.repositories;

import com.reinaldo.helpdesk.domain.Chamado;
import com.reinaldo.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
