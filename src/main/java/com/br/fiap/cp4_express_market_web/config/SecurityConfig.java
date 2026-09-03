package com.br.fiap.cp4_express_market_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeConfig -> {
                    authorizeConfig.requestMatchers("/", "/login", "/logout").permitAll();
                    authorizeConfig.requestMatchers("/css/**", "/js/**", "/images/**").permitAll();
                    // /error é o destino interno de todo 404/500. Sem liberar, um erro em rota
                    // pública (ex.: /css/arquivo-inexistente.css) viraria redirect para o login
                    // em vez de renderizar a página de erro. Rotas desconhecidas continuam
                    // privadas por causa do anyRequest().authenticated() abaixo.
                    authorizeConfig.requestMatchers("/error").permitAll();
                    authorizeConfig.requestMatchers("/market/**").authenticated();
                    authorizeConfig.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/market", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .build();
    }
}
