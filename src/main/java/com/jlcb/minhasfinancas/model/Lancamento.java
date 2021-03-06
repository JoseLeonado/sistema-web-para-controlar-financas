package com.jlcb.minhasfinancas.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.jlcb.minhasfinancas.model.enums.StatusLancamento;
import com.jlcb.minhasfinancas.model.enums.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lancamento", schema = "financas")
@Builder
@Data
@NoArgsConstructor /* Cria um construtor padrão */
@AllArgsConstructor /* Cria um constutor com todas propriedades da classe */
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "mes")
	private Integer mes;

	@Column(name = "ano")
	private Integer ano;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING) /* Diz que vamos guardar o nome que está no enum (RECEITA ou DESPESA) */
	private TipoLancamento tipo;

	@Column(name = "status")
	@Enumerated(EnumType.STRING) /* Diz que vamos guardar o nome que está no enum (PENDENTE, CANCELADO ou EFETIVADO) */
	private StatusLancamento status;

	@ManyToOne
	@JoinColumn(name = "id_usuario") /* Nome da coluna na tabela do banco de dados */
	private Usuario usuario;

	@Column(name = "data_cadastro") /* Nome da coluna na tabela do banco de dados */
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class) /* Converter LocalDate para date */
	private LocalDate dataCadastro;
}
