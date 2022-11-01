package com.reinaldo.helpdesk;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner{

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	TecnicoRepository tecnicoRepository;
	@Autowired
	ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico tec1 = new Tecnico(null, "Reinaldo", "09024919633",
				"reinaldo@gmail.com", "23");
		tec1.setPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus", "09008033322",
				"linus@gmail.com", "3435");

		Chamado cham1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,
				"Chamado 01", "Primeiro chamado",tec1,cli1);

		clienteRepository.saveAll(Arrays.asList(cli1));
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		chamadoRepository.saveAll(Arrays.asList(cham1));

	}
}
