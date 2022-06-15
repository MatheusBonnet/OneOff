package com.oneoff.aep.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oneoff.aep.data.PontoDTO;
import com.oneoff.aep.entities.Ponto;
import com.oneoff.aep.entities.User;
import com.oneoff.aep.exception.PontoException;
import com.oneoff.aep.facade.PontoFacade;
import com.oneoff.aep.repositories.IPontoRepository;

@Service
public class PontoServiceImpl implements IPontoService {

	public static final String MESSAGE_ERROR_PONTO_NOT_FOUND = "Ponto nao encontrado, tente novamente.";

	private static final String MENSAGEM_ERRO = "Erro interno no servidor, consulte o suporte";

	@Autowired
	private IPontoRepository produtoRepository;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private PontoFacade pontoFacade;

	private ModelMapper mapper;

	@Autowired
	public PontoServiceImpl(IPontoRepository produtoRepository, IUserService userService) {
		this.mapper = new ModelMapper();
		this.produtoRepository = produtoRepository;
		this.userService = userService;
	}

	@Override
	public Ponto deletar(Long id) {
		try {
			this.buscaPorId(id);
			this.produtoRepository.deleteById(id);
			return null;

		} catch (PontoException m) {
			throw m;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Page<Ponto> listarTodas(Pageable pageable) {
		try {
			Page<Ponto> pages = produtoRepository.findAll(pageable);
			return pages;

		} catch (Exception e) {
			throw new PontoException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public Ponto inserirPonto(PontoDTO pontoDto) {
		try {
	      return pontoFacade.registerPonto(pontoDto);
		} catch (Exception e) {
			throw new PontoException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Ponto atualizar(Ponto ponto) throws PontoException{
		try {

			Optional<Ponto> pontoOpt = produtoRepository.findById(ponto.getId());
			if(pontoOpt.isPresent()){
				return produtoRepository.save(ponto);
			}
			throw new PontoException(MESSAGE_ERROR_PONTO_NOT_FOUND, HttpStatus.NOT_FOUND);
		} catch (PontoException d) {
			throw new PontoException(MESSAGE_ERROR_PONTO_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Ponto buscaPorId(Long id) throws PontoException{
		try {
			Optional<Ponto> ponto = this.produtoRepository.findById(id);
			if (ponto.isPresent()) {
				return this.mapper.map(ponto.get(), Ponto.class);
			}
			throw new PontoException(MESSAGE_ERROR_PONTO_NOT_FOUND, HttpStatus.NOT_FOUND);
		} catch (PontoException m) {
			throw m;
		} catch (Exception e) {
			throw new PontoException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public List<Ponto> listarByUser() {
		try {
			User user = this.userService.findAuth();
			List<Ponto> pontos = produtoRepository.findAllByUserId(user.getId());
			return pontos;

		} catch (Exception e) {
			throw new PontoException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}