package br.apolo.common.config;

import org.springframework.context.annotation.*;

@Configuration
@ImportResource(value = "classpath:spring-security-context.xml")
public class SecurityConfig {}