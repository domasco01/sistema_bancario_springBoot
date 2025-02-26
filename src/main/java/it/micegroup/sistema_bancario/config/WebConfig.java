package it.micegroup.sistema_bancario.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Consente tutte le rotte
                .allowedOrigins("http://localhost:4200") // Specifica il dominio del frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metodi consentiti
                .allowedHeaders("Content-Type", "Authorization") // Header consentiti
                .allowCredentials(true); // Consente l'invio di credenziali
    }
}
