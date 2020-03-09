package br.com.kek.meufilme.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.kek.meufilme.domain.Filme;
import br.com.kek.meufilme.domain.Genero;

@RunWith(SpringRunner.class)
@DataJpaTest
class FilmeRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private FilmeRepository filmeRepository;

	@Test
	void buscarFilmesPorAno() {
		Filme filme = Filme.builder().nome("nome").dataLancamento(new Date()).build();

		entityManager.persist(filme);
		entityManager.flush();
		PageRequest pagina = PageRequest.of(0, 10);

		assertThat(filmeRepository.buscarFilmesPorAno(2020, pagina), not(empty()));

	}

	@Test
	void buscarFilmesPorGenero() {
		Genero genero = Genero.builder().nome("Terror").build();
		entityManager.persist(genero);

		Filme filme = Filme.builder().nome("nome").generos(Collections.singletonList(genero)).build();

		entityManager.persist(filme);
		entityManager.flush();
		PageRequest pagina = PageRequest.of(0, 10);

		assertThat(filmeRepository.buscarFilmesPorGenero(genero.getId(), pagina), not(empty()));

	}
}