package br.com.kek.meufilme.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kek.meufilme.dto.FilmeDTO;
import br.com.kek.meufilme.service.FilmeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/filmes")
@Api(value="API REST de Filmes")
public class FilmeController {

	@Autowired
	private FilmeService service;

	@ApiOperation(value="Retorna um filme unico")
	@GetMapping(value = "/{idFilme}")
	public FilmeDTO obter(@PathVariable Long idFilme) {
		return service.obter(idFilme);
	}

	@ApiOperation(value="Salva um filme")
	@PostMapping
	public FilmeDTO salvar(@RequestBody @Valid FilmeDTO filme) {
		return service.salvar(filme);
	}
	
	@ApiOperation(value="Deleta um filme")
	@DeleteMapping(value = "/{idFilme}")
	public void deletaFilme(@PathVariable Long idFilme) {
		service.remover(idFilme);
	}
	
	@ApiOperation(value="Atualiza um filme")
	@PutMapping
	public FilmeDTO atualizaFilme(@RequestBody @Valid FilmeDTO filme) {
		return service.alterar(filme);
	}

	@ApiOperation(value="Busca um filme por ano de forma paginada")
	@GetMapping(params = { "ano", "page", "size" })
	public List<FilmeDTO> buscarFilesPorAno(@RequestParam("ano") Integer ano, @RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		return service.buscarFilesPorAno(ano, PageRequest.of(page, size, Sort.by("id").ascending()));
	}

	@ApiOperation(value="Busca um filme por idGenero de forma paginada")
	@GetMapping(params = { "idGenero", "page", "size" })
	public List<FilmeDTO> buscarFilesPorGenero(@RequestParam("idGenero") Integer idGenero,
			@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		return service.buscarFilesPorGenero(idGenero, PageRequest.of(page, size));
	}

}
