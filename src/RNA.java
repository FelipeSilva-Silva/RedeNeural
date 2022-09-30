import java.util.List;

public class RNA {

    private double erro;
    private double taxaApredizado;
    private long epocas;
    private int numNeuroniosEntrada;
    private int numNeuroniosSaida;
    private int numCamadasIntermediarias;

    private List<Camada> camadas;

    private double[][] entradas;
    private double[] saidasEsperadas;


    public RNA(int numNeuroniosEntrada,int numCamadasIntermediarias, int numNeuroniosSaida, int epocas, double erro, double taxaApredizado){
        this.numNeuroniosEntrada = numNeuroniosEntrada;
        this.numCamadasIntermediarias = numCamadasIntermediarias;
        this.numNeuroniosSaida = numNeuroniosSaida;
        this.epocas = epocas;
        this.erro = erro;
        this.taxaApredizado = taxaApredizado;

        // criando camadas
        this.camadas.add(new Camada(numNeuroniosEntrada, numNeuroniosSaida));
        this.camadas.add(new Camada(numCamadasIntermediarias,numNeuroniosSaida));
    }

    public void treinarRNA(double[][] entradas, double[] saidasEsperadas){

        

    }
    
    


}
