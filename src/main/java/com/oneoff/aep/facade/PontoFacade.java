package com.oneoff.aep.facade;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.tomcat.util.buf.StringUtils;
import org.aspectj.weaver.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneoff.aep.data.PontoDTO;
import com.oneoff.aep.entities.Ponto;
import com.oneoff.aep.entities.User;
import com.oneoff.aep.facade.populators.Populator;
import com.oneoff.aep.repositories.IPontoRepository;
import com.oneoff.aep.services.IPontoService;
import com.oneoff.aep.services.IUserService;

import ch.qos.logback.classic.pattern.Util;

@Service
public class PontoFacade {
	
	@Autowired
	private Populator<PontoDTO, Ponto> pontoReversePopulator;
	
	@Autowired
	private IPontoRepository repository;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IPontoService pontoService;

	public Populator<PontoDTO, Ponto> getPontoReversePopulator() {
		return pontoReversePopulator;
	}

	public IPontoRepository getRepository() {
		return repository;
	}

	public void setRepository(IPontoRepository repository) {
		this.repository = repository;
	}

	public IPontoService getPontoService() {
		return pontoService;
	}

	public void setPontoService(IPontoService pontoService) {
		this.pontoService = pontoService;
	}

	public void setPontoReversePopulator(Populator<PontoDTO, Ponto> pontoReversePopulator) {
		this.pontoReversePopulator = pontoReversePopulator;
	}

	public Ponto registerPonto(PontoDTO pontoDTO) throws Exception {
		Ponto ponto = new Ponto();
		SimpleDateFormat diaFormatado = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat horaFormatada = new SimpleDateFormat("HH:mm:ss");
		
		if(pontoDTO != null) {
			ponto.setHorasEntrada(horaFormatada.parse(pontoDTO.getHoraEntrada().toString()));
			ponto.setHoraSaida(horaFormatada.parse(pontoDTO.getHoraSaida().toString()));
			ponto.setDate(diaFormatado.parse(pontoDTO.getDia().toString()));
			ponto.setDescricao(pontoDTO.getDescricao());
			getRepository().save(ponto);
		}
		return ponto;
	}

}
