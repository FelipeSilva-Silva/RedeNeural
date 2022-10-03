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

    private List<Camada> camadas;


    public RNA(int numNeuroniosEntrada, int numCamadasIntermediarias, int numNeuroniosSaida, int epocas, double erro,
            double taxaApredizado) {
        this.numNeuroniosEntrada = numNeuroniosEntrada;
        this.numCamadasIntermediarias = numCamadasIntermediarias;
        this.numNeuroniosSaida = numNeuroniosSaida;
        this.epocas = epocas;
        this.erro = erro;
        this.taxaApredizado = taxaApredizado;

        // criando camadas
        this.camadas = new ArrayList<Camada>();

        // Inicializar camadas
        this.camadas.add(new Camada(numNeuroniosEntrada, numCamadasIntermediarias));
        this.camadas.add(new Camada(numCamadasIntermediarias, numNeuroniosSaida));
    }

    public void treinarRNA(Double[][] entradas, Double[] saidasEsperadas) {

        Double erroTreino = 0.0;
        int numeroTreinoEntrada = entradas.length;
        int numTreinoInteracoes = 0;

        do {

            for (int i = 0; i < numeroTreinoEntrada; i++) {

                Double saidaEsperada = saidasEsperadas[i];
                Double[] entradaTeste = new Double[numNeuroniosEntrada];

                for (int j = 0; j < entradas[0].length; j++) {
                    entradaTeste[j] = entradas[i][j];
                }

                Double saida = InserirEntrada(entradaTeste);
                backPropagation(entradaTeste, saida, saidaEsperada);

                //Double erroEstimado = saidaEsperada - saida;
                //erroTreino = calcularErro(erroEstimado);
                erroTreino = saidaEsperada - saida;
            }

            numTreinoInteracoes++;

            //erroTreino >= this.erro
            //numTreinoInteracoes < epocas

            // Parar treino pelo erro
            if (erroTreino <= this.erro) {
                
                System.out.println("numero de interaçoes: " + numTreinoInteracoes);
                System.out.println("Erro: " + erro);

                break;

            }

        
        // Parar treino pelas iteraçoes
        } while (numTreinoInteracoes < epocas);

        System.out.println("Erro: " + erro);
    }

    public void backPropagation(Double[] entradas, Double saida, Double saidaEsperada) {

        for (int i = this.camadas.size() - 1; i >= 0; i--) {
            for (int j = 0; j < this.camadas.get(i).getNumNeuronioCamada(); j++) {

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
                    camadas.get(i).getNeuronioCamada(j).atuliazarPesosSaida(saida, saidaEsperada, taxaApredizado,
                            temporarioSaidas);

                } else {

                    temporarioEntradas.addAll(Arrays.asList(entradas));

                    camadas.get(i).getNeuronioCamada(j).atuliazarPesosIntermediaria(camadas.get(i).getNeuronioCamada(j).getSaida(), taxaApredizado, entradas,
                            camadas.get(i + 1).getNeuronioCamada(i).getGradiente(), camadas.get(i + 1).getPesosNeuronios(j));

                }
            }
        }
    }

    public Double InserirEntrada(Double[] entradas) {

        for (Camada camada : this.camadas) {
            entradas = calculoSomatorioAtivacao(camada, entradas);
        }

        Double saida = entradas[0];
        return saida;
    }

    // calcular NET e a saida com a funçao de ativaçao
    public Double[] calculoSomatorioAtivacao(Camada camada, Double[] entradas) {

        Double[] valoresDeSaida = new Double[camada.getNumNeuronioCamada()];

        for (int i = 0; i < valoresDeSaida.length; i++) {
            valoresDeSaida[i] = camada.getNeuronioCamada(i).somatorio(Arrays.asList(entradas));
            valoresDeSaida[i] = camada.getNeuronioCamada(i).fucaoAtivacao(valoresDeSaida[i]);
        }

        return valoresDeSaida;
    }

    public Double calcularErro(Double erroEstimado) {

        return Math.pow(erroEstimado, 2);

    }

}
