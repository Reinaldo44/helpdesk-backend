package com.reinaldo.helpdesk.service;

import com.reinaldo.helpdesk.domain.Chamado;
import com.reinaldo.helpdesk.domain.Cliente;
import com.reinaldo.helpdesk.domain.Tecnico;
import com.reinaldo.helpdesk.domain.enums.Perfil;
import com.reinaldo.helpdesk.domain.enums.Prioridade;
import com.reinaldo.helpdesk.domain.enums.Status;
import com.reinaldo.helpdesk.repositories.ChamadoRepository;
import com.reinaldo.helpdesk.repositories.ClienteRepository;
import com.reinaldo.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    TecnicoRepository tecnicoRepository;
    @Autowired
    ChamadoRepository chamadoRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    public void instanciaDB(){

        Tecnico tec1 = new Tecnico(null, "Reinaldo", "09024919633",
                "reinaldo@gmail.com", encoder.encode("1234"));
        tec1.setPerfil(Perfil.ADMIN);

        Tecnico tec2 = new Tecnico(null, "Roberto", "09024919689",
                "roberto@gmail.com",  encoder.encode("1236"));
        tec2.setPerfil(Perfil.ADMIN);

        Tecnico tec3 = new Tecnico(null, "Rafael", "09024919635",
                "rafael@gmail.com",  encoder.encode("12444"));
        tec3.setPerfil(Perfil.ADMIN);

        Tecnico tec4 = new Tecnico(null, "Reinaldo", "09024919634",
                "reinaldo2@gmail.com",  encoder.encode("0234"));
        tec4.setPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus", "09008033322",
                "linus@gmail.com",  encoder.encode("0034"));

        Chamado cham1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,
                "Chamado 01", "Primeiro chamado",tec1,cli1);

        clienteRepository.saveAll(Arrays.asList(cli1));
        tecnicoRepository.saveAll(Arrays.asList(tec1));
        tecnicoRepository.saveAll(Arrays.asList(tec2));
        tecnicoRepository.saveAll(Arrays.asList(tec3));
        tecnicoRepository.saveAll(Arrays.asList(tec4));
        chamadoRepository.saveAll(Arrays.asList(cham1));
    }

}
