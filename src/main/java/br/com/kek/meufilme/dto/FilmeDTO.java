package br.com.kek.meufilme.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "{filme.nomeFilme}")
	private String nome;
	
	@NotBlank(message = "{filme.sinopseFilme}")
	private String sinopse;
	
	@NotNull(message = "{filme.dataLancamentoFilme}")
	private Date dataLancamento;
	
	private List<GeneroDTO> generos;
}
