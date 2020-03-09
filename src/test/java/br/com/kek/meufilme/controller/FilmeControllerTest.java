package br.com.kek.meufilme.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import br.com.kek.meufilme.dto.FilmeDTO;
import br.com.kek.meufilme.service.FilmeService;

@RunWith(SpringRunner.class)
@WebMvcTest(FilmeController.class)
public class FilmeControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private FilmeService service;
	
	@Test
	public void obterFilmes() throws Exception {
		FilmeDTO filmeDTO = recuperarFilmeDTOMontado();
		Mockito.when(service.obter(Mockito.anyLong())).thenReturn(filmeDTO);
		ResultActions result = mvc.perform(get("/filmes/12").contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk()).andExpect(jsonPath("$.nome", is(filmeDTO.getNome())));
	}

	private FilmeDTO recuperarFilmeDTOMontado() {
		return FilmeDTO.builder().id(12L).nome("A volta dos que não foram").dataLancamento(new Date()).build();
	}

}
