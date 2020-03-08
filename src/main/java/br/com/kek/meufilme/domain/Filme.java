package br.com.kek.meufilme.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Filme {

	@Id
	@Column(name = "IDFILME")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOMEFILME")
	private String nome;
	
	@Column(name = "DESCSINOPSEFILME", length = 2000)
	private String sinopse;
	
	@Column(name = "DATALANCAMENTO")
	private Date dataLancamento;
	
	@ManyToMany
    @JoinTable(name = "GENERO_FILME",
        joinColumns = @JoinColumn(name = "IDFILME", referencedColumnName = "IDFILME"),
        inverseJoinColumns = @JoinColumn(name = "IDGENERO", referencedColumnName = "IDGENERO"))
	private List<Genero> generos;

}
