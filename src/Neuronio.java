import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Neuronio
 */
public class Neuronio {

    private List<Double> pesos;
    private Double saida;
    private Double gradiente;
    // private Double bias;

    public Neuronio(int entrada) {
        Random random = new Random();
        this.pesos = new ArrayList<Double>();

        for (int i = 0; i < entrada; i++) {
            this.pesos.add(random.nextDouble() * 2 - 1);
        }
    }

    public Double fucaoAtivacao(Double saida) {
        return this.saida = (1 / (1 + Math.exp(-saida)));
    }

    public Double somatorio(List<Double> entrada) {

        Double saida = 0.0;
        for (int i = 0; i < entrada.size(); i++) {
            saida += entrada.get(i) * this.pesos.get(i);
        }
        return saida;
    }

    public void atuliazarPesosSaida(Double saida, Double saidaEsperada, Double taxaAprendizado,
            List<Neuronio> SaidasItermediarias) {

        // derivada
        this.gradiente = saida * ((1 - saida) * (saidaEsperada - saida));

        for (int i = 0; i < this.pesos.size(); i++) {
            this.pesos.set(i, pesos.get(i) + taxaAprendizado * gradiente * SaidasItermediarias.get(i).saida);
        }

    }

    public void atuliazarPesosIntermediaria(Double saida, Double taxaAprendizado,
            Double[] EntradasDaRede, Double gradiente, List<Double> pesosDaSaida) {

        Double delta = 0.0;
        for (int i = 0; i < 2; i++) {
            delta += gradiente * this.pesos.get(i);
        }

        // derivada
        this.gradiente = saida * ((1 - saida) * delta);

        for (int i = 0; i < this.pesos.size(); i++) {
            this.pesos.set(i, this.pesos.get(i) + taxaAprendizado * this.gradiente * EntradasDaRede[i]);
        }

    }

    public List<Double> getPesos() { 
        return pesos;
    }

    public Double getPesos(int index) { 
        return pesos.get(index);
    }

    public Double getSaida() {
        return saida;
    }

    public Double getGradiente() {
        return gradiente;
    }

    
    

}