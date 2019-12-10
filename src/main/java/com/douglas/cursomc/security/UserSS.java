package com.douglas.cursomc.security;

import com.douglas.cursomc.domain.Enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe Usuario que atende o Contrato do Spring Security
 *
 */

public class UserSS implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS() {
    }

    public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
        super();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
    }

    /**
     * Retorna id
     * @return
     */
    public Integer getId(){
        return id;
    }

    /**
     * Retorna os perfis do usuario.
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Metodo que retona a senha
     * @return
     */
    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Metodo que checa se a conta está expirada.
     * Futuramente poderá criar alguma implementação.
     * @return true - caso a conta nãoe esteja expirada.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Metdo que checa se a classe está bloqueada
     * @return true - caso a conta não esteja bloqueada
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * As credenciais estão expiradas
     * Pode usar uma logica para expirar a senha do cliente
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checa se o usuario está ativo.
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
