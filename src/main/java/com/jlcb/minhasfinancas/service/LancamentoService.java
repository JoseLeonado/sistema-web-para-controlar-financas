package com.jlcb.minhasfinancas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.jlcb.minhasfinancas.model.Lancamento;
import com.jlcb.minhasfinancas.model.enums.StatusLancamento;

public interface LancamentoService {

	Lancamento salvar(Lancamento lancamento);

	Lancamento atualizar(Lancamento lancamento);

	void deletar(Lancamento lancamentoEncontrado);

	List<Lancamento> buscar(Lancamento lancamentoFiltros);
	
	void atulizarStatus(Lancamento lancamento, StatusLancamento status);
	
	Optional<Lancamento> obterLancamentoPorId(Long id);
	
	BigDecimal obterSaldoPorUsuario(Long id);
}
