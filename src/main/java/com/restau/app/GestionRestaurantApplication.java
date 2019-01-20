package com.restau.app;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.restau.app.services.StorageService;
 

@SpringBootApplication
public class GestionRestaurantApplication {
	@Resource
	StorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(GestionRestaurantApplication.class, args);
	}
	@Bean
	public SessionLocaleResolver localeResolver() {
		storageService.init();
	   SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
	   sessionLocaleResolver.setDefaultLocale(Locale.US);
	   return sessionLocaleResolver;
	}
	
}

