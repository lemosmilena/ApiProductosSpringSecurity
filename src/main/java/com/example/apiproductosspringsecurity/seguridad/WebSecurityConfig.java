package com.example.apiproductosspringsecurity.seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //establece las pautas de configuración

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override // configuramos la seguridad de nuestra aplicación
    protected void configure(HttpSecurity http) throws Exception {
        http     //devuelven un objeto HTTP, el mismo objeto. El orden es primordial!
                .csrf().disable() // suplantación de un request, alguien se hace pasar por un cliente Cliente <-> Hacker <-> Servidor
                                 // se utiliza cuando se trabaja con Front, por eso no lo usamos para diseños de APIs por eso la desactivamos con disable
                .authorizeRequests()
                .antMatchers("/", "/index.html").permitAll()  // mapea determinadas url, se le pueden aplicar ciertas restricciones
                                                                        // el "/" sirve para el localhost y se separa con coma las diferentes paginas, donde ingresan sin loguearse
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }
}
