package com.reinaldo.helpdesk.resource;
import com.reinaldo.helpdesk.domain.Tecnico;
import com.reinaldo.helpdesk.dtos.TecnicoDto;
import com.reinaldo.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id){

        Tecnico obj =  service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDto(obj));
    }
    @GetMapping
    public ResponseEntity<List<TecnicoDto>> findAll(){

        List<Tecnico> tecnicos = service.findAll();
        List<TecnicoDto> listDto = tecnicos.stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @PostMapping
    public ResponseEntity<TecnicoDto> create(@RequestBody TecnicoDto tecDto){

        Tecnico tecnico = service.created(tecDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
}
