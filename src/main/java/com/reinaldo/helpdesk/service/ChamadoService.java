package com.reinaldo.helpdesk.service;

import com.reinaldo.helpdesk.domain.Chamado;
import com.reinaldo.helpdesk.domain.Cliente;
import com.reinaldo.helpdesk.domain.Tecnico;
import com.reinaldo.helpdesk.domain.enums.Prioridade;
import com.reinaldo.helpdesk.domain.enums.Status;
import com.reinaldo.helpdesk.dtos.ChamadoDto;
import com.reinaldo.helpdesk.repositories.ChamadoRepository;
import com.reinaldo.helpdesk.service.exceptions.ObjectnotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TecnicoService tecnicoService;

    public Chamado findById(Integer id){

        Optional<Chamado> chamado = chamadoRepository.findById(id);

        return chamado.orElseThrow(()-> new ObjectnotFoundExceptions("Objeto não encontrado!" + id));
    }

    public Chamado create(ChamadoDto obj) {

       return chamadoRepository.save(newChamado(obj));
    }
    private Chamado newChamado(ChamadoDto obj){

        Cliente cliente = clienteService.findById(obj.getCliente());
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());

        Chamado chamado = new Chamado();

        if(obj.getId() != null){
             chamado.setId(obj.getId());
        }

        chamado.setCliente(cliente);
        chamado.setTecnico(tecnico);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setObservacoes(obj.getObservacoes());
        chamado.setTitulo(obj.getTitulo());

        return chamado;
    }

    public List<Chamado> findAll() {

       List<Chamado> chamado = chamadoRepository.findAll();
       return chamado;
    }
}
