package br.com.yagovcb.transacoes.handler;

import br.com.yagovcb.transacoes.services.dto.DetalheErroDTO;
import br.com.yagovcb.transacoes.services.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class TransactionExceptionHandler {

    /**
     * Método responsavel por tratar a excessão quando ocorrer
     *
     * @param bad tipo da excessão
     * @param request requisição feita
     *
     * @return retorna a excessão em formato ResponseEntity
     * */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<DetalheErroDTO> handleBadRequestException(BadRequestException bad, HttpServletRequest request) {
        DetalheErroDTO erro = new DetalheErroDTO();
        erro.setStatus(400L);
        erro.setMensagem(bad.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
