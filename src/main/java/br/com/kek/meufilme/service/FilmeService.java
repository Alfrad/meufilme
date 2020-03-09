package br.com.kek.meufilme.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import br.com.kek.meufilme.domain.Filme;
import br.com.kek.meufilme.dto.FilmeDTO;
import br.com.kek.meufilme.exception.GeneroNaoCadastradoException;
import br.com.kek.meufilme.exception.RegistroNaoEncontradoException;
import br.com.kek.meufilme.exception.UpdateRegistroSemIdentificadorException;
import br.com.kek.meufilme.repository.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	public FilmeDTO salvar(FilmeDTO filme) {
		try {
			Filme filmeSalvo = repository.save(modelMapper.map(filme, Filme.class));
			return modelMapper.map(filmeSalvo, FilmeDTO.class);
		} catch (JpaObjectRetrievalFailureException ex) {
			throw new GeneroNaoCadastradoException("excecao.generoNaoExiste");
		}
	}
	
	public FilmeDTO alterar(FilmeDTO filme) {
		if(filme.getId() == null) {
			throw new UpdateRegistroSemIdentificadorException("excecao.registroSemIdentificador");
		}
		return salvar(filme);
	}

	public FilmeDTO obter(Long idFilme) {
		return modelMapper.map(
				repository.findById(idFilme)
						.orElseThrow(() -> new RegistroNaoEncontradoException("excecao.nenhumRegistroEncontrado")),
				FilmeDTO.class);
	}

	public List<FilmeDTO> buscarFilmesPorAno(Integer ano, Pageable pageable) {
		return repository.buscarFilmesPorAno(ano, pageable).stream()
				.map(f -> modelMapper.map(f, FilmeDTO.class))
				.collect(Collectors.toList());
	}

	public List<FilmeDTO> buscarFilmesPorGenero(Integer idGenero, Pageable page) {
		return repository.buscarFilmesPorGenero(idGenero, page).stream()
				.map(f -> modelMapper.map(f, FilmeDTO.class))
				.collect(Collectors.toList());
	}

	public void remover(Long idFilme) {
		// aproveitando exceção caso não exista um filme retornando 404
		FilmeDTO filme = obter(idFilme);
		repository.deleteById(filme.getId());
	}
}
