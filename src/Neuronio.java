import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Neuronio
 */
public class Neuronio {

    private List<Double> pesos;
    private double saida;

    public Neuronio(int entrada) {
        Random random = new Random();
        this.pesos = new ArrayList<Double>();

        for (int i = 0; i < entrada; i++) {
            this.pesos.add(random.nextDouble() * 2 - 1);
        }
    }

   

}