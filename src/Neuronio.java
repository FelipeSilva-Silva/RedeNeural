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

    public Double fucaoAtivacao(Double saida, int funcao) {

        switch (funcao) {
            case 1:
                this.saida = (1 / (1 + Math.exp(-saida)));
                break;

            case 2:
                this.saida = (2 / (1 + Math.exp(-2 * saida)) - 1);
                ;
                break;
        }
        return this.saida;
    }

    public Double somatorio(List<Double> entrada) {

        Double saida = 0.0;
        for (int i = 0; i < entrada.size(); i++) {
            saida += entrada.get(i) * this.pesos.get(i);
        }
        return saida;
    }

    public void atuliazarPesosSaida(Double saida, Double saidaEsperada, Double taxaAprendizado,
            List<Neuronio> SaidasItermediarias, int funcao) {

        // derivada

        switch (funcao) {
            case 1:
                this.gradiente = saida * ((1 - this.saida) * (saidaEsperada - this.saida));
                break;

            case 2:
                this.gradiente = (1 - Math.pow(this.saida, 2)) * (saidaEsperada - this.saida);
                break;
        }

        for (int i = 0; i < this.pesos.size(); i++) {
            this.pesos.set(i, this.pesos.get(i) + taxaAprendizado * this.gradiente * SaidasItermediarias.get(i).saida);
        }

    }

    public void atuliazarPesosIntermediaria(Double saidaIntermediaria, Double taxaAprendizado,
            Double[] EntradasDaRede, Double gradiente, int indexSaida, Double saidaEsperada, Double saidaCamadaSaida,
            int funcao) {

        Double delta = 0.0;
        delta += gradiente * this.pesos.get(0);

        switch (funcao) {
            case 1:
                this.gradiente = saidaIntermediaria * ((1 - saidaIntermediaria) * delta);
                break;

            case 2:
                this.gradiente = (1 - Math.pow(saidaIntermediaria, 2)) * delta;
                break;
        }

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