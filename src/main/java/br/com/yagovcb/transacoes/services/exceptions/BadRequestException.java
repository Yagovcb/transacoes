package br.com.yagovcb.transacoes.services.exceptions;

public class BadRequestException extends RuntimeException {
    /**
     * Método que chama o construtor padrão de RuntimeException
     * @param mensagem a ser exibida
     * */
    public BadRequestException(String mensagem) {
        super(mensagem);
    }
}
