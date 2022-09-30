import java.util.ArrayList;
import java.util.List;

public class Camada {

    public List<Neuronio> neuronios;

    public Camada(int entrada, int neuronios){

        this.neuronios = new ArrayList<Neuronio>();

        for (int i = 0; i < neuronios; i++) {
            this.neuronios.add(new Neuronio(entrada));
        }

    }

    
    
}