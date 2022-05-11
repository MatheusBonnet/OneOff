package com.oneoff.aep.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oneoff.aep.DTO.PontoDTO;
import com.oneoff.aep.entities.Ponto;

public interface IPontoService {

	public Ponto atualizar(final Ponto ponto);
	
	public Ponto deletar(final Long id);
	
	public Page<Ponto> listarTodas(Pageable pageable);
	
	public Ponto buscaPorId(final Long id);
	
	public Ponto inserirPonto(final PontoDTO ponto);
	
	public List<Ponto> listarByUser();

}
