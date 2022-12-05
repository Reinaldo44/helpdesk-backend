package com.reinaldo.helpdesk.repositories;

import com.reinaldo.helpdesk.domain.Pessoa;
import com.reinaldo.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);

}
