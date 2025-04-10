package org.example.tchat.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
open class SpringSecurityConfig {

    @Autowired
    open fun configureGlobal(auth : AuthenticationManagerBuilder) {
        val encoder = BCryptPasswordEncoder()

        //Créer des utilisateurs fixes
        auth.inMemoryAuthentication()
            .passwordEncoder(encoder)
            .withUser("aaa")
                .password(encoder.encode("bbb"))
                .roles("USER")
            .and()
            .withUser("Admin")
                .password(encoder.encode("Admin"))
                .roles("ADMIN")
    }

    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorize ->
            authorize
                .requestMatchers("/testPrivate").authenticated()
                .requestMatchers("/tchat/allMessages").authenticated()
                .requestMatchers("/testPrivateAdmin").hasRole("ADMIN")
                .requestMatchers("/tchat/saveMessage").hasRole("ADMIN")
                .anyRequest().permitAll()
        }
            .httpBasic { }
            .formLogin { }
            .csrf{c-> c.disable()}
        return http.build()
    }
}