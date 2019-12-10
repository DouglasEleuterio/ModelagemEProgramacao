package com.douglas.cursomc.service;

import com.douglas.cursomc.domain.Cliente;
import com.douglas.cursomc.repositories.ClienteRepository;
import com.douglas.cursomc.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cli = repo.findByEmail(email);
        if(email == null){
            throw new UsernameNotFoundException("E-mail não encontrado no banco de dados: "+email);
        }
        return new UserSS(cli.getId(),cli.getEmail(),cli.getSenha(),cli.getPerfis());
    }
}
