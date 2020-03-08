package br.com.kek.meufilme.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.kek.meufilme.domain.Filme;

@Repository
public interface FilmeRepository extends PagingAndSortingRepository<Filme, Long> {
 
	@Query("SELECT F FROM Filme F WHERE YEAR(F.dataLancamento) = ?1")
    List<Filme> buscarFilesPorAno(Integer ano, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT DISTINCT F.* FROM GENERO_FILME GF INNER JOIN FILME F ON F.IDFILME = GF.IDFILME WHERE GF.IDGENERO = ?1")
	List<Filme> buscarFilesPorGenero(Integer idGenero, PageRequest pageable);
}
