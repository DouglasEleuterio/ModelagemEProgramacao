package com.douglas.cursomc.security;

import com.douglas.cursomc.dto.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Filtro que intecepta uma requisição.
 * Analisa os dados da requisição e libera o acesso dependendo das regras que for definido.
 * Ao inteceptar a requisição, verificará se o Usuário e senha está correto.
 * Ao extender UsernamePasswordAuthenticationFilter o spring vai interceptar a requisição e fazer o filtro.
 */
public class JWTAutenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    public JWTAutenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Metodo que autentica usuário.
     * Recebe os dados que virá na requisição.
     * Insere as informações dentro de um objeto do tipo CredenciaisDTO
     * Insere as informações obtidas pelo DTO em um Objeto do Tipo UsernamePasswordAuthenticationToken, passando Email,
     *  Senha e Lista de Credenciais.
     * Insere toas as informaçeõs em um objeto do tipo Authentication.
     * O objeto Authentication será passado para o método seguinte.
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @see CredenciaisDTO
     * @see UsernamePasswordAuthenticationToken
     * @see Authentication
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request
                                                , HttpServletResponse response) throws AuthenticationException {
        try{
            CredenciaisDTO credenciaisDTO = new ObjectMapper()
                    .readValue(request.getInputStream(),CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credenciaisDTO.getEmail(), credenciaisDTO.getSenha(),new ArrayList<>());
            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Após a autenticação ser efetuada com sucesso, chamamos esse metodo.
     * O Objetivo é gerar um Token e acrescenta-lo na resposta da requisição.
     * Primeiro pegamos o nome de usuario que foi realizado a autenticação no metodo attemptAuthentication.
     *  Perceba que na assinatura do método, ele já recebe um objeto contendo a resposta do metodo anterior. (Authentication authResult)
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        String username = ((UserSS) authResult.getPrincipal()).getUsername(); //getPrincipal retorna um usuario do Spring. Fazemos um Casting
        String token = jwtUtil.generateToken(username); //obtem um token
        response.addHeader("Authorization","Bearer "+ token); // Retorna o Token no cabecalho da resposta.
    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json());
        }

        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                    + "\"status\": 401, "
                    + "\"error\": \"Não autorizado\", "
                    + "\"message\": \"Email ou senha inválidos\", "
                    + "\"path\": \"/login\"}";
        }
    }
}