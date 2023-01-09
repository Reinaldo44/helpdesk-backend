package com.reinaldo.helpdesk.resource;
import com.reinaldo.helpdesk.domain.Cliente;
import com.reinaldo.helpdesk.domain.dtos.ClienteDto;
import com.reinaldo.helpdesk.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService clienteService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable Integer id) {
        Cliente obj = clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDto(obj));
    }
    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll(){
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDto> listDto = clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    
    @PostMapping
    public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto cliDto){
        Cliente cliente = clienteService.create(cliDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Integer id, @RequestBody ClienteDto cliDto){
        Cliente Cliente = clienteService.update(id, cliDto);
        return ResponseEntity.ok().body(new ClienteDto(Cliente));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
