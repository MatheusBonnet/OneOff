package com.oneoff.aep.data;

import org.springframework.hateoas.RepresentationModel;

public class PontoDTO extends RepresentationModel<PontoDTO>{
	
	private Long id;
	
	private String descricao;
	
	private String horaEntrada;
	
	private String horaSaida;
	
	private String dia;
	
	public PontoDTO() {
	}

	


	public PontoDTO(Long id, String descricao, String horaEntrada, String horaSaida, String dia) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		this.dia = dia;
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

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}
}
