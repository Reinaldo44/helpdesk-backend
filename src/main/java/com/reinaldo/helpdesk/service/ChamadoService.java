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

import java.time.LocalDate;
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

    public List<Chamado> findAll() {

        List<Chamado> chamado = chamadoRepository.findAll();
        return chamado;
    }

    public Chamado create(ChamadoDto obj) {

       return chamadoRepository.save(newChamado(obj));
    }
    public Chamado update(Integer id, ChamadoDto objDto){

        objDto.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDto);
        return chamadoRepository.save(oldObj);

    }
    private Chamado newChamado(ChamadoDto obj){

        Cliente cliente = clienteService.findById(obj.getCliente());
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());

        Chamado chamado = new Chamado();

        if(obj.getId() != null){
             chamado.setId(obj.getId());
        }
        if(obj.getStatus().equals(2)){

            chamado.setDataFechamento(LocalDate.now());

        }

        chamado.setCliente(cliente);
        chamado.setTecnico(tecnico);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setObservacoes(obj.getObservacoes());
        chamado.setTitulo(obj.getTitulo());

        return chamado;
    }


}
