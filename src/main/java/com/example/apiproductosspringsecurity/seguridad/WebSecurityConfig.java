package com.example.apiproductosspringsecurity.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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

    @Bean // le pido que ejecute esto una vez y  cada vez que pida el passwordEncoder me devuelva ese objeto
        // lo ejecuta una vez y al resultado lo debe guardar, cada vez que lo llame devuelve el mismo objeto
        // suele ir en otra clase, retorna un passwordEncoder, retorna un nuevo
        // esto es una entidad, necesito tenerlo instanciado
        // dame un objeto para encriptar las contraseñas
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5); // El BCrypt es el algoritmo que le agrega la salt automaticamente dentro del mismo hash
        // toma la password y automaticamente lo hashea y le agrega la salt en el mismo hash, vamos a tener un unico campo clave.
        // PasswordEncoder es una interfaz que tiene un metodo matches para saber si la clave es igual al hash
        //strength, cuanto mas grande sea mas vueltas va a dar el hashing, por eso es mas pesado el algoritmo
        // LO QUE OBTENEMOS ES:
        // $2a significa BCrypt, $05$ es el número de vueltas (strength // tiene un crecimiento exponencial), u5aasdFSFASFAx13qCpB. (sin el punto, desde ahi es el hash) es la salt y el resto es el HASH
        // siempre cambia la SALT
    }

    @Override // es un services (es la capa de noegocios que define los accesos a las entidades. Le provee al controlador los grupos de entidades. Me permite filtrar las entidades, interactua con el modelo)
    // me permite acceder a entidades, me permite acceder a entidades UserDetails (tiene todos los detaller de un usuario (rol, permiso, user, pass))
    @Bean
    protected UserDetailsService userDetailsService() {

        // creamos nuestros usuarios en memoria, con un mapa y el patron de diseño builder. Devolviendo un objeto usuario,
        // al cual se le pueden agregar detalles.
        // builder es un patron de diseño muy utilizado

        UserDetails usuario1 = User.builder() // agregarle permisos, wpa expirada, cuenta desactivada
                .username("milena")
                .password(passwordEncoder().encode("unafacil")) // devuelve un User.buider(), es decir me devuelve el mismo objeto para ir trabajando
                .roles("ADMIN") // puede ser un enum
                // no se finaliza con builder porque devuelve un objeto User y no UserDetails
                .build(); // se construye el objeto final y devuelve un UserDetails
                // el orden depende de cada caso, no es tan importante como vaya
        UserDetails usuario2 = User.builder() // construye un usuario
                .username("maria")
                .password(passwordEncoder().encode("unanofacil")) // debemos usar el PasswordEncoder
                .roles("USER") // varios usuarios pueden tener este rol, porque solamente define el tipo y los accesos/permisos, que es lo que puede hacer o no
                .build();
        UserDetails usuario3 = User.builder()
                .username("emma")
                .password(passwordEncoder().encode("unanotanfacil"))
                .roles("ADMIN")
                .build(); // construye


        // http://localhost:8080/login?logout, para desloguearse

                // creo un objeto de tipo InMemoryUserDetailsManager
        return new InMemoryUserDetailsManager(usuario1, usuario2, usuario3); // se cargan en la db de memoria con un mapa
        // no hay una db y se guarda en la memoria de la maquina, por eso siempre estan creados.


        //AMBOS metodos son interfaces que devuelven un objeto concreto que implementa la interfaz. "Habla como habla el UserDetails"
        // y recibe parametros (constructor), tres variables que se crean porque no tenemos db
    }





}
