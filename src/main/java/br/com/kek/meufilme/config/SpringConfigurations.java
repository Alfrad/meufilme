package br.com.kek.meufilme.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfigurations {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
