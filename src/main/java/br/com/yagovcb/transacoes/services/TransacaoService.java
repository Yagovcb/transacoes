package br.com.yagovcb.transacoes.services;

import br.com.yagovcb.transacoes.services.dto.TransacaoDTO;
import br.com.yagovcb.transacoes.util.ControleExceptionUtil;
import br.com.yagovcb.transacoes.util.GeradoresUtil;
import br.com.yagovcb.transacoes.util.TransacaoUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    /**
     * Método que retorna uma Lista de transações aleatorias
     *
     * @param ano passado via requisição
     * @param id do usuario, passado via requisicao
     * @param mes vigente, passado via requisicao
     *
     * @throws HttpServerErrorException quando um dos parametros passados não estiver dentro das metricas estabelecidas
     *
     * @return List<TransacaoDTO> lista de transações
     *
     * */
    public List<TransacaoDTO> retornaListaTransacoes(int id, int ano, int mes) {
        List<TransacaoDTO> transacoes = new ArrayList<>();
        if (ControleExceptionUtil.validaAnoMesId(ano, mes, id)){
            adicionaTransacao(id, ano, mes, transacoes, getTotalTransacoes(id, mes));
        }
        return transacoes;
    }

    /**
     * Método responsavel adicionar um Objeto TransacaoDTO a lista de transações da requisição
     *
     * @param ano passado via requisição
     * @param id do usuario, passado via requisicao
     * @param mes vigente, passado via requisicao
     * @param totalTransacoes para a requisicao
     * @param transacoes lista de transações da requisicao
     * */
    private void adicionaTransacao(int id, int ano, int mes, List<TransacaoDTO> transacoes, int totalTransacoes) {
        for (int indice = 0; indice < totalTransacoes; indice++ ){
            transacoes.add(montaDTO(id, ano, mes, indice));
        }
    }

    /**
     * Método responsavel por retornar a quantidade de transações da requisição
     *
     * @param id do usuario, passado via requisicao
     * @param mes vigente, passado via requisicao
     *
     * @return numero de transações da requisicao
     * */
    private int getTotalTransacoes(int id, int mes) {
        return retornaDigitoUm(id) * mes;
    }

    /**
     * Método responsavel por setar os atributos no DTO
     *
     * @param ano passado via requisição
     * @param id do usuario, passado via requisicao
     * @param mes vigente, passado via requisicao
     * @param indice indice do laço
     *
     * @return Objeto TransacaoDTO
     * */
    private TransacaoDTO montaDTO(int id, int ano, int mes, int indice) {
        TransacaoDTO dto = new TransacaoDTO();
        dto.setDescricao(TransacaoUtil.retornaStringAleatoria(retornaDigitoUm(id)));
        dto.setValor(retornaValorTransacao(id, indice, mes));
        dto.setData(retornaDataEmLong(ano, mes));
        return dto;
    }

    /**
     * Método responsavel por recuperar a data em formato Long
     *
     * @param ano passado via requisição
     * @param mes vigente, passado via requisicao
     *
     * @return data em formato timestamp
     * */
    private Long retornaDataEmLong(int ano, int mes) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(ano, mes, LocalDateTime.now().getDayOfMonth()), LocalTime.now());
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp.getTime();
    }

    /**
     * Método responsavel por retornar um Integer unico para o atributo valor de TransacaoDTO
     *
     * @param id do usuario, passado via requisicao
     * @param mes vigente, passado via requisicao
     * @param indice indice do laço
     *
     * @return atributo valor de TransacaoDTO
     * */
    private Integer retornaValorTransacao(int id, int indice, int mes){
        indice = indice == 0 ? 1: indice;
        Integer valor = id * indice * mes;

        valor = valor > GeradoresUtil.getValorMaximo() ? GeradoresUtil.getValorMaximo() : valor;

        return valor;
    }

    /**
     * Método responsavel por retornar o primeiro digito do id do Usuario informado na requisição
     *
     * @param number id do usuario
     *
     * @return digito referente ao id da requisição
     * */
    private int retornaDigitoUm(int number){
        String str = Integer.toString(number);
        int digits = 1;

        if(digits > str.length()){
            return number;
        }

        return Integer.parseInt(str.substring(0, str.startsWith("-") ? digits + 1 : digits));
    }
}
