package org.samtar.cms.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.samtar.cms.modules.accesscontrols.user.service.imps.UserDetailImps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
private UserDetailImps userDetailsImp;
private ObjectMapper mapper;
    private final JwtFilterChain jwtFilterChain;

    public SecurityConfig(ObjectMapper mapper, UserDetailImps userDetails,JwtFilterChain jwtFilterChain) {
        this.mapper = mapper;
        this.userDetailsImp = userDetails;
        this.jwtFilterChain = jwtFilterChain;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public  AuthenticationManager authenticationManager(AuthenticationConfiguration cfg){
        return cfg.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsImp);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
     return http.csrf(AbstractHttpConfigurer::disable)
             .authorizeHttpRequests(
                     auth->auth
                             .requestMatchers("/api/auth/**","/api/testing/unprotected")
                             .permitAll()
                             .anyRequest()
                             .authenticated()
             )
             .sessionManagement(session->session
                     .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             .authenticationProvider(authenticationProvider())
             .addFilterBefore(jwtFilterChain, UsernamePasswordAuthenticationFilter.class)
             .exceptionHandling(ex -> ex
                     .authenticationEntryPoint((HttpServletRequestreq,  response,authException)->{
                         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                         response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                         mapper.writeValue(response.getWriter(),"something went wrong");
                     }))
             .build();
    }

}


