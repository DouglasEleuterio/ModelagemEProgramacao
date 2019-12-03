package com.douglas.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Classe de Configuração que mapeia os caminhos de acesso para determinados usuarios.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;
    // Lista de páginas permitidas para acesso.
    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**"
    };
    // Lista de páginas liberadas para acesso, desde de que seja utilizando o metodo GET
    private static final String[] PUBLIC_MATCHERS_GET = {
            "/produtos/**",
            "/categorias/**",
            "/clientes/**"

    };

    /**
     * <h3>Configurando os acessos</h3>
     * Libera o acesso ao console do H2.
     * O acesso será liberado somente se o perfil de 'test' for utilizado para subir a aplicação.
     * Desativa a Proteção CSRF, essa proteção se aplica a servidores stateFull que guarda a seção do usuário,
     * o que não se aplica em nosso caso.
     * Libera recurso de CORS, haja visto que acessaremos nossos endPoints de vários maneiras, Ex: Web, Mobile, Etc...
     * Permite acesso a /Produtos e /Categorias apenas nos Metodos HTTP GET.
     * Dessa forma, usuarios comuns poderão apenas listar produtos e categorias, nunca deletar ou alterar.
     * Para todos os demais caminhos que não consta como permitAll, será acessado apenas via autenticação.
     * Por fim, deixamos claro que o Servidor não deve armazenar seção dos usuarios.
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    /**
     * <h3>Configura as definições de CORS</h3>
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    /**
     * Metodo que Encripta a senha do usuario.
     * @return senha Criptografada.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
