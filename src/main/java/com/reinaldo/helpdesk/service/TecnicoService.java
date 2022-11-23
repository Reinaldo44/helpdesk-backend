package com.reinaldo.helpdesk.service;

import com.reinaldo.helpdesk.domain.Tecnico;
import  com.reinaldo.helpdesk.repositories.TecnicoRepository;
import com.reinaldo.helpdesk.service.exceptions.ObjectnotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundExceptions("Objeto não encontrado" + id));
    }
    public List<Tecnico> findAll(){
        List<Tecnico> tecnicos = repository.findAll();
        return tecnicos;
    }

}
