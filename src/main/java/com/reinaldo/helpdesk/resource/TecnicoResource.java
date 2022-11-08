package com.reinaldo.helpdesk.resource;
import com.reinaldo.helpdesk.domain.Tecnico;
import com.reinaldo.helpdesk.dtos.TecnicoDto;
import com.reinaldo.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    public ResponseEntity<List<Tecnico>> findAll(){

        List<Tecnico> tecnicos = service.findAll();
        return ResponseEntity.ok().body(tecnicos);

    }


}
