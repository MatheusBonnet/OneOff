package com.oneoff.aep.facade;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneoff.aep.DTO.PontoDTO;
import com.oneoff.aep.entities.Ponto;
import com.oneoff.aep.entities.User;
import com.oneoff.aep.facade.populators.Populator;
import com.oneoff.aep.repositories.IPontoRepository;
import com.oneoff.aep.services.IPontoService;
import com.oneoff.aep.services.IUserService;

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
		Date hora = Calendar.getInstance().getTime();
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		User user = this.userService.findAuth();
		getPontoReversePopulator().populate(pontoDTO, ponto);
		ponto.setUsers(user);
		ponto.setHoras(horaFormatada.format(hora));
		ponto.setDate(diaFormatado.format(data));
		getRepository().save(ponto);
		return ponto;
	}

}
