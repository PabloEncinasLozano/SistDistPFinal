package com.project.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;



import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
public class SecurityConfig {


    @Autowired
    public UserDetailsService userDetailsService;


    /**
     * Configuración de la autenticación.
     * 
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }

    /**
     * Configuración del codificador de contraseñas.
     * 
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * Configuración del AuthenticationManager.
     * 
     * @param config AuthenticationConfiguration
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    /**
     * Configuración del SecurityFilterChain.
     * 
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Configuracion de login
        http.formLogin(form -> form  //-------Esto dice que hacer en caso de que no haya login, haya error o otros casos
                        .loginPage("/login")
                        .loginProcessingUrl("/login") 
                        .usernameParameter("email")  
                        .failureUrl("/login?error=true") //Para mandar el error en caso de fallo
                        .defaultSuccessUrl("/",true)
                        .permitAll()
                );
        
        // Configuracion de logout
        http.logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID") // Eliminar la cookie de sesión

                );

        // Configuracion de reglas de acceso
        http.authorizeHttpRequests()
                .requestMatchers("/usuarios/login").permitAll() // Permitir acceso a la pagina de login
                .requestMatchers("/usuarios/registro").permitAll() // Permitir acceso a la pagina de registro
                .requestMatchers("/usuarios/logout").permitAll() // Permitir acceso a la pagina de logout
                .requestMatchers("/usuarios/logout/msg").permitAll() // Permitir acceso a la pagina de logout


                //Aqui se meten las direcciones del controlador que no requieren login.
                .requestMatchers("/","/register","/login","/pokeAPI").permitAll()
                .anyRequest().authenticated()

                
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied");
            

        return http.build();
    }

    //Esto es para indicar al sistema que los roles de los usuarios empiezan por ROLE_
    @Bean
    static GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("ROLE_");
    }
}
