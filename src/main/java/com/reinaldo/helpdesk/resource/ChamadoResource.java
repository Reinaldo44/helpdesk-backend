package com.reinaldo.helpdesk.resource;
import com.reinaldo.helpdesk.domain.Chamado;
import com.reinaldo.helpdesk.dtos.ChamadoDto;
import com.reinaldo.helpdesk.dtos.TecnicoDto;
import com.reinaldo.helpdesk.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/chamados")
public class ChamadoResource {
    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id){

        Chamado chamado = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDto(chamado));

    }
    @GetMapping
    public ResponseEntity<List<ChamadoDto>> findAll(){

        List<Chamado> chamado = chamadoService.findAll();
        List<ChamadoDto> objList = chamado.stream().map(obj -> new ChamadoDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(objList);

    }
    @PostMapping
    public ResponseEntity<ChamadoDto> create(@Valid @RequestBody ChamadoDto obj){

        Chamado chamado = chamadoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
