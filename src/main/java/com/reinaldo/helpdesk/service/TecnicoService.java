package com.reinaldo.helpdesk.service;

import com.reinaldo.helpdesk.domain.Pessoa;
import com.reinaldo.helpdesk.domain.Tecnico;
import com.reinaldo.helpdesk.dtos.TecnicoDto;
import com.reinaldo.helpdesk.repositories.PessoaRepository;
import  com.reinaldo.helpdesk.repositories.TecnicoRepository;
import com.reinaldo.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.reinaldo.helpdesk.service.exceptions.ObjectnotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecenicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecenicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundExceptions("Objeto não encontrado" + id));
    }
    public List<Tecnico> findAll(){
        List<Tecnico> tecnicos = tecenicoRepository.findAll();
        return tecnicos;
    }

    public Tecnico create(TecnicoDto tecDto) {

        tecDto.setId(null);
        validaPorCpfEmail(tecDto);
        Tecnico newObj = new Tecnico(tecDto);
        return tecenicoRepository.save(newObj);
    }

    private void validaPorCpfEmail(TecnicoDto tecDto) {

        Optional<Pessoa> obj = pessoaRepository.findByCpf(tecDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != tecDto.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
        }

        obj = pessoaRepository.findByEmail(tecDto.getEmail());

        if(obj.isPresent() && obj.get().getId() != tecDto.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema.");
        }

    }

    public Tecnico update(Integer id,@Valid TecnicoDto tecDto) {
        tecDto.setId(id);
        validaPorCpfEmail(tecDto);
        Tecnico tecnico = findById(id);
        tecnico = new Tecnico(tecDto);
        return tecenicoRepository.save(tecnico);

    }

    public void delete(Integer id) {

        Tecnico tecnico = findById(id);
        if(tecnico.getChamados().size() > 0){
           throw new DataIntegrityViolationException("O técnico possui chamados em seu nome, não pode ser deletdo!");
        }
        else {

            tecenicoRepository.deleteById(id);
        }

    }
}
