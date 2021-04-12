package br.com.yagovcb.transacoes.util;

import br.com.yagovcb.transacoes.services.exceptions.BadRequestException;

public class ControleExceptionUtil {

    private static final int QUATRO = 4;
    private static final int SEIS = 6;

    /**
     * Construtor vazio padrão
     * */
    private ControleExceptionUtil() { throw new IllegalStateException("Utility class");}


    /**
     * Método responsavel por verificar os atributos da requisição, conforme padronização
     *
     * @param ano passado via requisição
     * @param id do usuario, passado via requisicao
     * @param mes vigente, passado via requisicao
     *
     * @return verdadeiro se atributos da requisição estão conforme padronização
     * */
    public static boolean validaAnoMesId(Integer ano, Integer mes, Integer id) throws BadRequestException {
        int compId  = id.toString().length();

        if (ano.toString().length() != QUATRO) {
            throw new BadRequestException("O ano deve conter de 4 caracteres. Favor inserir um ano válido");
        }
        if (!GeradoresUtil.getListaMes().contains(mes)) {
            throw new BadRequestException("O mês deve ser inserido no formado de 1 a 12, " +
                    "onde 1 é Janeiro e 12, Dezembro. Favor inserir um mês válido");
        }
        if(compId < QUATRO || compId > SEIS){
            throw new BadRequestException("O ID de Usuario fornecido deve conter de 4 a 6 digitos." +
                    " Favor inserir um ID de Usuario válido");
        }
        return true;
    }
}
