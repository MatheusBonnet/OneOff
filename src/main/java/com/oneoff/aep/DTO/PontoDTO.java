package com.oneoff.aep.DTO;

import org.springframework.hateoas.RepresentationModel;

public class PontoDTO extends RepresentationModel<PontoDTO>{
	
	private Long id;
	
	private String descricao;
	
	public PontoDTO() {
	}

	public PontoDTO(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
