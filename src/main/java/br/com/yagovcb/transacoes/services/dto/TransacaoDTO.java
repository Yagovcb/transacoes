package br.com.yagovcb.transacoes.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDTO {

    private String descricao;

    private long data;

    private Integer valor;

}
