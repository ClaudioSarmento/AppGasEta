package devandroid.claudio.appgaseta.apoio;

public class UtilGasEta {
    public void metodoNaoEstatico(){};
    public static void metodoEstatico(){};

    public static String calcularMelhorOpcao(double gasolina, double etanol){
        double precoIdeal = gasolina * 0.70;
        String mensagemRetorno;

        if(etanol <= precoIdeal){
            mensagemRetorno = "Abastecer com Etanol";
        }else{
            mensagemRetorno = "Abastecer com Gasolina";
        }

        /* Se o preco do etanol for igual ou menor que o preco ideal
        *  melhor abastecer com etanol, caso contrário a gasolina é mais vantagem.
        * */

        return mensagemRetorno; // Abastecer com Gasolina - Abastecer com Etanol
    }
}
