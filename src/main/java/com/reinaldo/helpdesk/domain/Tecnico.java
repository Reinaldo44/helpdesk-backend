package com.reinaldo.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reinaldo.helpdesk.domain.enums.Perfil;
import com.reinaldo.helpdesk.dtos.TecnicoDto;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        setPerfil(Perfil.CLIENTE);
    }

    public Tecnico(TecnicoDto obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        setPerfil(Perfil.CLIENTE);
    }
}

