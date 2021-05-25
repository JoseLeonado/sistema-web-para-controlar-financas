package com.jlcb.minhasfinancas.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.jlcb.minhasfinancas.model.Lancamento;
import com.jlcb.minhasfinancas.model.enums.StatusLancamento;
import com.jlcb.minhasfinancas.model.repository.LancamentoRepository;
import com.jlcb.minhasfinancas.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {
	
	private LancamentoRepository lancamentoRepository;
	
	/* "Injetando" a dependência */
	public LancamentoServiceImpl(LancamentoRepository lancamentoRepository) {
		this.lancamentoRepository = lancamentoRepository;
	}

	@Override
	@Transactional
	public Lancamento salvar(@Valid Lancamento lancamento) {
		
		lancamento.setStatus(StatusLancamento.PENDENTE); /* Todo lançamento novo terá o status de PENDENTE */
		
		return lancamentoRepository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(@Valid Lancamento lancamento) {
		
		Objects.requireNonNull(lancamento.getId()); /* Checando se o id não é nulo, ou seja, verificar se está sendo passado um id */
		
		return salvar(lancamento);
	}

	@Override
	public void deletar(Lancamento lancamento) {

		Objects.requireNonNull(lancamento.getId());

		lancamentoRepository.delete(lancamento);
	}

	@Override
	@Transactional
	public List<Lancamento> buscar(Lancamento lancamentoFiltros) {

		/*
		 * Essa é uma api que realiza consultas com filtros passados no parâmetro de forma automática
		 */
		Example<Lancamento> example = Example.of(lancamentoFiltros, 
				ExampleMatcher.matching()
				.withIgnoreCase() /* Realiza a busca ignorando as letras maiúsculas e ,inúsculas  */
				.withStringMatcher(StringMatcher.CONTAINING /* Realiza a busca através da String digitada pelo usuário, onde irá trazer o resultado mesmo digitando somente uma letra da palavra */)); 
		
		return lancamentoRepository.findAll(example);
	}

	@Override
	public void atulizarStatus(Lancamento lancamento, StatusLancamento status) {
		
		lancamento.setStatus(status);
		
		atualizar(lancamento);
	}

	@Override
	public Optional<Lancamento> obterLancamentoPorId(Long id) {
		return lancamentoRepository.findById(id);
	}
}