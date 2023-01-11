package com.reinaldo.helpdesk.service;

import java.util.Optional;

import com.reinaldo.helpdesk.domain.Tecnico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.reinaldo.helpdesk.domain.Pessoa;
import com.reinaldo.helpdesk.repositories.PessoaRepository;
import com.reinaldo.helpdesk.security.UserSS;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService{
    @Autowired
    private PessoaRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<Pessoa> user = repository.findByEmail(email);
        if(user == null) {

            throw new UsernameNotFoundException(email);

        }
        return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfis());
    }
}
