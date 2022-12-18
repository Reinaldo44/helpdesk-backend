package com.reinaldo.helpdesk.service;

import com.reinaldo.helpdesk.domain.Pessoa;
import com.reinaldo.helpdesk.domain.Cliente;
import com.reinaldo.helpdesk.dtos.ClienteDto;
import com.reinaldo.helpdesk.repositories.PessoaRepository;
import com.reinaldo.helpdesk.repositories.ClienteRepository;
import com.reinaldo.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.reinaldo.helpdesk.service.exceptions.ObjectnotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundExceptions("Objeto não encontrado" + id));
    }
    public List<Cliente> findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    public Cliente create(ClienteDto cliDto) {

        cliDto.setId(null);
        validaPorCpfEmail(cliDto);
        Cliente newObj = new Cliente(cliDto);
        return clienteRepository.save(newObj);
    }

    private void validaPorCpfEmail(ClienteDto cliDto) {

        Optional<Pessoa> obj = pessoaRepository.findByCpf(cliDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != cliDto.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
        }

        obj = pessoaRepository.findByEmail(cliDto.getEmail());

        if(obj.isPresent() && obj.get().getId() != cliDto.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema.");
        }

    }

    public Cliente update(Integer id,@Valid ClienteDto cliDto) {
        cliDto.setId(id);
        validaPorCpfEmail(cliDto);
        Cliente Cliente = findById(id);
        Cliente = new Cliente(cliDto);
        return clienteRepository.save(Cliente);

    }

    public void delete(Integer id) {

        Cliente Cliente = findById(id);
        if(Cliente.getChamados().size() > 0){
           throw new DataIntegrityViolationException("O técnico possui chamados em seu nome, não pode ser deletdo!");
        }
        else {

            clienteRepository.deleteById(id);
        }

    }
}
