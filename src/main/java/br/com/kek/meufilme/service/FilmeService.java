package br.com.kek.meufilme.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.kek.meufilme.domain.Filme;
import br.com.kek.meufilme.dto.FilmeDTO;
import br.com.kek.meufilme.exception.RegistroNaoEncontradoException;
import br.com.kek.meufilme.repository.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	public FilmeDTO salvar(FilmeDTO filme) {
		Filme filmeSalvo = repository.save(modelMapper.map(filme, Filme.class));
		return modelMapper.map(filmeSalvo, FilmeDTO.class);
	}

	public FilmeDTO obter(Long idFilme) {
		return modelMapper.map(repository.findById(idFilme)
				.orElseThrow(() -> new RegistroNaoEncontradoException("Nenhum Registro Encontrado!")), FilmeDTO.class);
	}

	public List<FilmeDTO> buscarFilesPorAno(Integer ano, Pageable pageable) {
		return repository.buscarFilesPorAno(ano, pageable).stream().map(f -> modelMapper.map(f, FilmeDTO.class))
				.collect(Collectors.toList());
	}

	public List<FilmeDTO> buscarFilesPorGenero(Integer idGenero, PageRequest pageable) {
		return repository.buscarFilesPorGenero(idGenero, pageable).stream().map(f -> modelMapper.map(f, FilmeDTO.class))
				.collect(Collectors.toList());
	}

	public void remover(Long idFilme) {
		FilmeDTO filme = obter(idFilme);
		repository.deleteById(filme.getId());
	}
}
