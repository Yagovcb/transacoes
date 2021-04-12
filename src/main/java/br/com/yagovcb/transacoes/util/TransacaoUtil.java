package br.com.yagovcb.transacoes.util;

import java.util.Locale;
import java.util.Random;

public class TransacaoUtil {
    private static final String[] VOGAIS = {"a","e","i","o","u"};
    private static final String[] CONSOANTES = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","x","y","z"};

    private static final Random random = new Random();

    /**
     * Construtor vazio padrão
     * */
    private TransacaoUtil() { throw new IllegalStateException("Utility class");}

    /**
     * Método responsavel por montar a String que será atribuida ao atributo descrição de TransacaoDTO
     *
     * @param primeiroDigitoIdUsuario passado via requisição
     *
     * @return String final para o atributo descrição de TransacaoDTO
     * */
    public static String retornaStringAleatoria(int primeiroDigitoIdUsuario){
        String palavraRetorno;
        int quantidadeCaracteres = getQuantidadeCaracteres();

        palavraRetorno = defineLetraInicial(primeiroDigitoIdUsuario);

        for (int i = 1; i < quantidadeCaracteres; i++){
            if ( palavraRetorno.length() == quantidadeCaracteres){
                break;
            } else {
                palavraRetorno = montaString(palavraRetorno);
            }
        }
        return palavraRetorno;
    }

    /**
     * Método que monta a String conforme regra de negocio
     * @return palavraRetorno conforme regra
     * */
    private static String montaString(String palavraRetorno) {
        palavraRetorno = adicionaEspaco(palavraRetorno);
        palavraRetorno = adicionaNovaletra(palavraRetorno, getRandom());
        return palavraRetorno;
    }

    /**
     * Método retorna a letra inicial da string a ser montada
     * @return palavraRetorno a ser montada
     * */
    private static String defineLetraInicial(int primeiroDigitoIdUsuario) {
        String palavraRetorno;
        if (isPar(primeiroDigitoIdUsuario)) {
            palavraRetorno = getProximaLetra(getRandom(), VOGAIS).toUpperCase(Locale.ROOT);
        } else {
            palavraRetorno = getProximaLetra(getRandom(), CONSOANTES).toUpperCase(Locale.ROOT);
        }
        return palavraRetorno;
    }

    /**
     * Método responsavel por validar viabilidade de espaço em branco na string
     * @return true se palavra for grande o suficiente para receber um espaço
     * */
    private static String adicionaEspaco(String palavraRetorno) {
        if (palavraRetorno.length() > GeradoresUtil.getQtdMinima() && VerificadoresUtil.palavraOitoOuSeisOuMultiplo(palavraRetorno)) {
            if (!VerificadoresUtil.verificaUltimaLetra(VerificadoresUtil.getUltimoCaracter(palavraRetorno)) ){
                palavraRetorno = concatena(getProximaLetra(random, VOGAIS), palavraRetorno);
                palavraRetorno = concatena(" " , palavraRetorno);
                return palavraRetorno;
            }
            if (VerificadoresUtil.verificaUltimaLetra(VerificadoresUtil.getUltimoCaracter(palavraRetorno)) && !VerificadoresUtil.verificaUltimasCincoLetras(palavraRetorno)){
                palavraRetorno = concatena(" " , palavraRetorno);
                return palavraRetorno;
            }
        }
        return palavraRetorno;
    }

    /**
     * Método responsavel por trazer a quantidade de caracteres de cada palavra de forma dinamica
     *
     * @return quantidadeCaracteres que a palavra devera possuir
     *
     * */
    private static int getQuantidadeCaracteres() {
        int resultado = Math.min((getRandom().nextInt(GeradoresUtil.getQtdMaximaPalavra()) * GeradoresUtil.getQtdMaximaPalavra()), GeradoresUtil.getQtdMaxima());
        return Math.max(resultado, GeradoresUtil.getQtdMinima());
    }

    /**
     * Método responsavel por adicionar uma nova letra para o atributo descricao de TransacaoDTO
     *
     * @param retorno descricao a ser retornada
     * @param random instancia do objeto Random
     *
     * @return string com nova letra concatenada
     * */
    private static String adicionaNovaletra(String retorno, Random random) {
        String anterior = VerificadoresUtil.getUltimoCaracter(retorno);
        if(anterior.equals("q")){
            retorno = concatena("u", retorno);
        } else if (GeradoresUtil.getListaVogais().contains(anterior)){
            retorno = concatena(getProximaLetra(random, CONSOANTES), retorno);
        } else {
            retorno = concatena(getProximaLetra(random, VOGAIS), retorno);
        }
        return retorno;
    }

    /**
     * Método responsavel por adicionar nova letra a string padrao
     *
     * @param atual String com letras ja adicionadas
     * @param nova nova letra a ser adicionada
     *
     * @return String apos nova letra ser adicionada
     * */
    private static String concatena(String nova, String atual){
        return atual.concat(nova);
    }

    /**
     * Método responsavel por retornar nova letra a ser adicionada
     *
     * @param random instancia do objeto Random
     * @param caracter Array de letras para verificacao
     *
     * @return retorna nova letra a ser adicionada
     * */
    private static String getProximaLetra(Random random, String[] caracter) {
        return caracter[random.nextInt(caracter.length)];
    }

    /**
     * Método responsavel por retornar se o resto da divisao for par com base no primeiro digito do ID do usuario
     *
     * @param primeiroDigitoIdUsuario primeiro digito do ID do usuario
     *
     * @return retorna verdadeiro se o resto da divisao for par
     * */
    private static boolean isPar(int primeiroDigitoIdUsuario) {
        return primeiroDigitoIdUsuario % 2 == 0;
    }

    /**
     * Método responsavel por retornar uma instancia de Random
     * */
    public static Random getRandom() {
        return random;
    }
}
