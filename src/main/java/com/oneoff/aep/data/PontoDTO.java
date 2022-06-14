package com.oneoff.aep.data;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class PontoDTO extends RepresentationModel<PontoDTO>{
	
	private Long id;
	
	private String descricao;
	
	private Date horaEntrada;
	
	private Date horaSaida;
	
	private Date dia;
	
	private String aguardandoAprovacao;
	
	public PontoDTO() {
	}

	


	public PontoDTO(Long id, String descricao, Date horaEntrada, Date horaSaida, Date dia, String aguardandoAprovacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		this.dia = dia;
		this.aguardandoAprovacao = aguardandoAprovacao;
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

	public String getAguardandoAprovacao() {
		return aguardandoAprovacao;
	}

	public void setAguardandoAprovacao(String aguardandoAprovacao) {
		this.aguardandoAprovacao = aguardandoAprovacao;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}
}
