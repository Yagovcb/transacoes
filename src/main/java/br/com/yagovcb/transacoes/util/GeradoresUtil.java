package br.com.yagovcb.transacoes.util;

import java.util.Arrays;
import java.util.List;

public class GeradoresUtil {
    private static final String[] LETRAS_FINAL = {"r", "s", "l"};
    private static final String[] VOGAIS = {"a","e","i","o","u"};
    private static final Integer[] MES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    private static final int VALOR_MAXIMO = 9999999;
    private static final int QTD_MINIMA = 10;
    private static final int QTD_MAXIMA = 60;
    private static final int QTD_MAXIMA_PALAVRA = 8;

    /**
     * Construtor vazio padrão
     * */
    private GeradoresUtil() { throw new IllegalStateException("Utility class");}


    /**
     * Método auxiliar responsavel por retornar um ArrayList de Strings
     *
     * @return List<String> com as vogais do alfabeto
     * */
    public static List<String> getListaLetrasFinal() {
        return Arrays.asList(LETRAS_FINAL);
    }

    /**
     * Método auxiliar responsavel por retornar um ArrayList de Strings
     *
     * @return List<String> com as vogais do alfabeto
     * */
    public static List<String> getListaVogais() {
        return Arrays.asList(VOGAIS);
    }

    /**
     * Método auxiliar responsavel por retornar um ArrayList de Integer
     *
     * @return List<Integer> com os meses do ano
     * */
    public static List<Integer> getListaMes() {
        return Arrays.asList(MES);
    }

    /**
     * Método auxiliar responsavel por retornar o valor maximo do atributo valor de TransacaoDTO
     *
     * @return valor maximo do atributo valor de TransacaoDTO
     * */
    public static Integer getValorMaximo () { return VALOR_MAXIMO; }

    /**
     * Método auxiliar responsavel por retornar o valor maximo de cada palavra
     *
     * @return valor maximo de cada palavra
     * */
    public static Integer getQtdMaximaPalavra() { return QTD_MAXIMA_PALAVRA; }

    /**
     * Método auxiliar responsavel por retornar o valor minimo para o atributo descricao de TransacaoDTO
     *
     * @return valor minimo para o atributo descricao de TransacaoDTO
     * */
    public static Integer getQtdMinima() { return QTD_MINIMA; }

    /**
     * Método auxiliar responsavel por retornar o valor maxima para o atributo descricao de TransacaoDTO
     *
     * @return valor maxima para o atributo descricao de TransacaoDTO
     * */
    public static Integer getQtdMaxima() { return QTD_MAXIMA; }
}
