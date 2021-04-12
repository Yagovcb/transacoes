package br.com.yagovcb.transacoes.util;

import java.util.Locale;

public class VerificadoresUtil {

    private static final int SEIS = 6;

    /**
     * Construtor vazio padrão
     * */
    private VerificadoresUtil() { throw new IllegalStateException("Utility class");}


    /**
     * Método responsavel por verificar se a letra anterior, previamente adicionada na string é 'S', 'R' ou é uma vogal
     *
     * @param letra formada até o momento
     *
     * @return retorna verdadeiro se ultima letra for 'S', 'R' ou é uma vogal
     * */
    public static boolean verificaUltimaLetra(String letra){
        return GeradoresUtil.getListaLetrasFinal().contains(letra) || GeradoresUtil.getListaVogais().contains(letra);
    }

    /**
     * Método responsavel por verificar se nas ultimas 7 letras da String, contem um espaço em branco
     * garante que não seja formada palavras com menos de 5 caracteres
     * @return true se tiver espaço em branco
     * */
    public static boolean verificaUltimasCincoLetras(String palavraRetorno) {
        return palavraRetorno.substring(palavraRetorno.length() - 7).contains(" ");
    }

    /**
     * Método responsavel por verificar se a string é divisivel por 8
     *
     * @param retorno descricao a ser retornada
     *
     * @return verdadeiro se o resto da divisao for par
     * */
    public static boolean palavraOitoOuSeisOuMultiplo(String retorno) {
        return retorno.length() % GeradoresUtil.getQtdMaximaPalavra() == 0 || retorno.length() % SEIS == 0;
    }


    /**
     * Método responsavel por retornar a letra cadastrada anteriormente
     *
     * @param retorno descricao a ser retornada
     *
     * @return letra cadastrada anteriormente
     * */
    public static String getUltimoCaracter(String retorno) {
        return retorno.substring(retorno.length() - 1).toLowerCase(Locale.ROOT);
    }

}
