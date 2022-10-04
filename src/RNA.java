import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RNA {

    private int numNeuroniosEntrada;
    private int numNeuroniosSaida;
    private int numCamadasIntermediarias;

    private Double erro;
    private Double taxaApredizado;
    private long epocas;
    private int fucao;

    private List<Camada> camadas;

    public RNA(int numNeuroniosEntrada, int numCamadasIntermediarias, int numNeuroniosSaida, int epocas, double erro,
            double taxaApredizado, int funcao) {
        this.numNeuroniosEntrada = numNeuroniosEntrada;
        this.numCamadasIntermediarias = numCamadasIntermediarias;
        this.numNeuroniosSaida = numNeuroniosSaida;
        this.epocas = epocas;
        this.erro = erro;
        this.taxaApredizado = taxaApredizado;
        this.fucao = funcao;

        // criando camadas
        this.camadas = new ArrayList<Camada>();

        // Inicializar camadas
        this.camadas.add(new Camada(numNeuroniosEntrada, numCamadasIntermediarias));
        this.camadas.add(new Camada(numCamadasIntermediarias, numNeuroniosSaida));
    }

    public void treinarRNA(Double[][] entradas, Double[][] saidasEsperadas) {

        Double erroTreino = 0.0;
        int numeroTreinoEntrada = entradas.length;
        int numTreinoInteracoes = 0;

        do {

            for (int i = 0; i < numeroTreinoEntrada; i++) {

                Double saidaEsperada = 0.0;
                Double saida = 0.0;

                for (int k = 0; k < numNeuroniosSaida; k++) {
                    int valorNeuronioSaida = k;
                    saidaEsperada = saidasEsperadas[i][k];
                    Double[] entradaTeste = new Double[numNeuroniosEntrada];

                    for (int j = 0; j < entradas[0].length; j++) {
                        entradaTeste[j] = entradas[i][j];
                    }

                    saida = InserirEntrada(entradaTeste)[k];
                    backPropagation(entradaTeste, saida, saidaEsperada, valorNeuronioSaida);

                }

                // System.out.println("interação: " + numTreinoInteracoes++);
                // System.out.println("Resultado esperado: " + saidasEsperadas[i][0] + " | " +
                // "Resultado da rede: "
                // + camadas.get(1).getNeuronioCamada(0).getSaida());
                // System.out.println("Resultado esperado: " + saidasEsperadas[i][1] + " | " +
                // "Resultado da rede: "
                // + camadas.get(1).getNeuronioCamada(1).getSaida());
                // System.out.println("----------------------------------------------------");

                // Double erroEstimado = saidaEsperada - saida;
                // erroTreino = calcularErro(erroEstimado);
                erroTreino = saidaEsperada - saida;
            }

            numTreinoInteracoes++;

            // erroTreino >= this.erro
            // numTreinoInteracoes < epocas

            // Parar treino pelo erro

            /*
             * 
             * if (erroTreino <= this.erro) {
             * 
             * System.out.println("numero de interaçoes: " + numTreinoInteracoes);
             * System.out.println("Erro: " + erro);
             * 
             * break;
             * 
             * }
             * 
             */

            // Parar treino pelas iteraçoes
        } while (numTreinoInteracoes < epocas);

        System.out.println("Erro: " + erro);
    }

    public void backPropagation(Double[] entradas, Double saida, Double saidaEsperada, int valorNeuronioSaida) {

        for (int i = this.camadas.size() - 1; i >= 0; i--) {

            // Recebe saidas da camada intermediaria ou as entradas da rede
            // para calculo do backpropagation
            // novoPeso = (pesoAntigo + taxaDeAprendisado * gradiente *
            // saidasDaCamadaIntermediaria) -> para a camada de saida
            // novoPeso = (pesoAntigo + taxaDeAprendisado * gradiente * Entradas) -> para a
            // camada de intermediaria
            List<Neuronio> temporarioSaidas = new ArrayList<Neuronio>();
            List<Double> temporarioEntradas = new ArrayList<Double>();

            if (i > 0) {

                temporarioSaidas.addAll(camadas.get(i - 1).getNeuronios());
                camadas.get(i).getNeuronioCamada(valorNeuronioSaida).atuliazarPesosSaida(saida, saidaEsperada,
                        taxaApredizado,
                        temporarioSaidas, this.fucao);

            } else {
                for (int j = 0; j < this.camadas.get(i).getNumNeuronioCamada(); j++) {

                    temporarioEntradas.addAll(Arrays.asList(entradas));

                    camadas.get(i).getNeuronioCamada(j).atuliazarPesosIntermediaria(
                            camadas.get(i).getNeuronioCamada(valorNeuronioSaida).getSaida(), taxaApredizado, entradas,
                            camadas.get(i + 1).getNeuronioCamada(valorNeuronioSaida).getGradiente(), j, saidaEsperada,
                            camadas.get(i + 1).getNeuronioCamada(valorNeuronioSaida).getSaida(), this.fucao);

                }
            }
        }
    }

    public Double[] InserirEntrada(Double[] entradas) {

        for (Camada camada : this.camadas) {
            entradas = calculoSomatorioAtivacao(camada, entradas);
        }

        return entradas;

    }

    // calcular NET e a saida com a funçao de ativaçao
    public Double[] calculoSomatorioAtivacao(Camada camada, Double[] entradas) {

        Double[] valoresDeSaida = new Double[camada.getNumNeuronioCamada()];

        for (int i = 0; i < valoresDeSaida.length; i++) {
            valoresDeSaida[i] = camada.getNeuronioCamada(i).somatorio(Arrays.asList(entradas));
            valoresDeSaida[i] = camada.getNeuronioCamada(i).fucaoAtivacao(valoresDeSaida[i], this.fucao);
        }

        return valoresDeSaida;
    }

    public Double calcularErro(Double erroEstimado) {

        return Math.pow(erroEstimado, 2);

    }

}
