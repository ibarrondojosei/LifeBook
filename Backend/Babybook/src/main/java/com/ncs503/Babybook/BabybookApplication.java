package com.ncs503.Babybook;

import com.ncs503.Babybook.auth.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableConfigurationProperties(RsaKeyProperties.class)
public class BabybookApplication {

	public static void main(String[] args) {

		SpringApplication.run(BabybookApplication.class, args);
	}

	//borrar despues de testear
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedHeaders("*")
						.allowedMethods("*")
						.allowCredentials(false)
						.allowedOrigins("*");
			}
		};
	}

}
