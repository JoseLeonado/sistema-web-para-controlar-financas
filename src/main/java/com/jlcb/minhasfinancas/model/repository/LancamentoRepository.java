package com.jlcb.minhasfinancas.model.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jlcb.minhasfinancas.model.Lancamento;
import com.jlcb.minhasfinancas.model.enums.TipoLancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	@Query(value =  
		   "SELECT SUM(l.valor) "
		 + "FROM Lancamento l JOIN l.usuario u "
		 + "WHERE u.id = :idUsuario AND l.tipo = :tipoLancamento "
		 + "GROUP BY u ")
	BigDecimal obterSaldoPorTipoDeLancamentoDeUmUsuario(@Param("idUsuario") Long idUsuario, @Param("tipoLancamento") TipoLancamento tipoLancamento);
}
