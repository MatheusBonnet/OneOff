package com.oneoff.aep.facade.populators;

import org.springframework.stereotype.Service;

import com.oneoff.aep.data.PontoDTO;
import com.oneoff.aep.entities.Ponto;

@Service
public class PontoPopulator implements Populator<Ponto, PontoDTO>{

	@Override
	public void populate(Ponto source, PontoDTO target) {
		target.setId(source.getId());
		target.setDescricao(source.getDescricao());
	}

	

}
