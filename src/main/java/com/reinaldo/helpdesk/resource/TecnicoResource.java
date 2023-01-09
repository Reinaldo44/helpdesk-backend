package com.reinaldo.helpdesk.resource;
import com.reinaldo.helpdesk.domain.Tecnico;
import com.reinaldo.helpdesk.domain.dtos.TecnicoDto;
import com.reinaldo.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto tecDto){

        Tecnico tecnico = service.create(tecDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> update(@PathVariable Integer id, @RequestBody TecnicoDto tecDto){


        Tecnico tecnico = service.update(id, tecDto);

        return ResponseEntity.ok().body(new TecnicoDto(tecnico));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> delete(@PathVariable Integer id){

        service.delete(id);
        return ResponseEntity.noContent().build();

    }

}
