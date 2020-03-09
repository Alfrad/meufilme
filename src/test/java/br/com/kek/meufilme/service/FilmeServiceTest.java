package br.com.kek.meufilme.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.kek.meufilme.domain.Filme;
import br.com.kek.meufilme.dto.FilmeDTO;
import br.com.kek.meufilme.exception.GeneroNaoCadastradoException;
import br.com.kek.meufilme.exception.RegistroNaoEncontradoException;
import br.com.kek.meufilme.exception.UpdateRegistroSemIdentificadorException;
import br.com.kek.meufilme.repository.FilmeRepository;
import br.com.kek.meufilme.utils.ExtrairMensagemUtil;

@RunWith(SpringRunner.class)
public class FilmeServiceTest {

	@Autowired
	private FilmeService service;

	@MockBean
	private FilmeRepository repository;

	@TestConfiguration
	static class FilmeServiceTestContextConfiguration {
		
		@Bean
		public FilmeService filmeService() {
			return new FilmeService();
		}
		
		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}
		
		@Bean
		public ExtrairMensagemUtil extrairMensagemUtil() {
			return new ExtrairMensagemUtil();
		}
		
		@Bean
		public MessageSource messageSource() {
			ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
			messageSource.setBasename("classpath:i18n/messages");
			messageSource.setDefaultEncoding("UTF-8");
			return messageSource;
		}

		@Bean
		public LocalValidatorFactoryBean getValidator() {
			LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
			bean.setValidationMessageSource(messageSource());
			return bean;
		}

	}
	
	@Test(expected = GeneroNaoCadastradoException.class)
	public void salvar_semGeneroCadastrado() {
		Mockito.when(repository.save(Mockito.any(Filme.class))).thenThrow(new GeneroNaoCadastradoException("excecao.generoNaoExiste"));
		service.salvar(recuperarFilmeDTOMontado());
	}
	
	@Test
	public void salvar() {
		Mockito.when(repository.save(Mockito.any(Filme.class))).thenReturn(recuperarFilmeMontado());
		assertThat(service.salvar(recuperarFilmeDTOMontado()), notNullValue());
	}
	
	@Test
	public void buscarFilmesPorGenero() {
		PageRequest pagina = PageRequest.of(0, 10);
		Mockito.when(repository.buscarFilmesPorGenero(Mockito.anyInt(), Mockito.any())).thenReturn(Collections.singletonList(recuperarFilmeMontado()));
		assertThat(service.buscarFilmesPorGenero(12, pagina), not(empty()));
	}
	
	@Test
	public void buscarFilmesPorAno() {
		PageRequest pagina = PageRequest.of(0, 10);
		Mockito.when(repository.buscarFilmesPorAno(Mockito.anyInt(), Mockito.any())).thenReturn(Collections.singletonList(recuperarFilmeMontado()));
		assertThat(service.buscarFilmesPorAno(2020, pagina), not(empty()));
	}
	
	@Test
	public void alterarFilme() {
		Filme filme = recuperarFilmeMontado();
		Mockito.when(repository.save(Mockito.any())).thenReturn(filme);
		assertThat(service.alterar(recuperarFilmeDTOMontado()), notNullValue());
	}
	
	@Test(expected = UpdateRegistroSemIdentificadorException.class)
	public void alterarFilme_identificadorNulo() {
		FilmeDTO filme = recuperarFilmeDTOMontado();
		filme.setId(null);
		service.alterar(filme);
	}
	
	@Test
	public void removerFilme() {
		Filme filme = recuperarFilmeMontado();
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(filme));
		service.remover(filme.getId());
	}
	
	@Test(expected = RegistroNaoEncontradoException.class)
	public void removerFilme_naoEncontrado() {
		Filme filme = recuperarFilmeMontado();
	    service.remover(filme.getId());
	}
	
	@Test
	public void obterFilme() {
		Filme filme = recuperarFilmeMontado();
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(filme));
		assertThat(service.obter(12L), notNullValue());
	}
	
	private Filme recuperarFilmeMontado() {
		return Filme.builder().id(12L).nome("A volta dos que não foram").dataLancamento(new Date()).build();
	}

	private FilmeDTO recuperarFilmeDTOMontado() {
		return FilmeDTO.builder().id(12L).nome("A volta dos que não foram").dataLancamento(new Date()).build();
	}

	@Test(expected = RegistroNaoEncontradoException.class)
	public void obterFilme_naoEncontrado() {
		service.obter(12L);
	}

}
