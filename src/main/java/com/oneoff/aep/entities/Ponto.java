package com.oneoff.aep.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Ponto {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@JsonInclude(Include.NON_EMPTY)
	private String descricao;
	
	@Column(name = "hora_entrada")
	private Date horasEntrada;
	
	@Column(name = "hora_saida")
	private Date horaSaida;
	
	@Column(name = "dd_mm_yyyy")
	private Date date;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "aguardando_aprovacao")
	private String aguardandoAprovacao;
	
	
	public Ponto() {
	}
	
	public Ponto(Long id, String descricao, Date horasEntrada, Date horaSaida, Date date, User user,
			String aguardandoAprovacao) {
		this.id = id;
		this.descricao = descricao;
		this.horasEntrada = horasEntrada;
		this.horaSaida = horaSaida;
		this.date = date;
		this.user = user;
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

	public User getUsers() {
		return user;
	}

	public void setUsers(User user) {
		this.user = user;
	}
	
	public String getAguardandoAprovacao() {
		return aguardandoAprovacao;
	}

	public void setAguardandoAprovacao(String aguardandoAprovacao) {
		this.aguardandoAprovacao = aguardandoAprovacao;
	}
	
	public Date getHorasEntrada() {
		return horasEntrada;
	}

	public void setHorasEntrada(Date horasEntrada) {
		this.horasEntrada = horasEntrada;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ponto other = (Ponto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
