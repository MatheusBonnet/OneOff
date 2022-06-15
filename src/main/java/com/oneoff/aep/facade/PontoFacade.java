package com.oneoff.aep.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneoff.aep.data.PontoDTO;
import com.oneoff.aep.entities.Ponto;
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
		
		if(pontoDTO != null) {
			ponto.setHorasEntrada(pontoDTO.getHoraEntrada());
			ponto.setHoraSaida(pontoDTO.getHoraSaida());
			ponto.setDate(pontoDTO.getDia());
			ponto.setDescricao(pontoDTO.getDescricao());
			ponto.setUser(userService.findAuth());
			getRepository().save(ponto);
		}
		return ponto;
	}

}
