package com.oneoff.aep.facade.populators;

import org.springframework.stereotype.Service;

import com.oneoff.aep.DTO.PontoDTO;
import com.oneoff.aep.entities.Ponto;

@Service
public class PontoReversePopulator implements Populator<PontoDTO, Ponto>{

	@Override
	public void populate(PontoDTO source, Ponto target) {
		target.setId(source.getId());
		target.setDescricao(source.getDescricao());
	}

	

}
