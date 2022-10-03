import java.util.ArrayList;
import java.util.List;

public class Camada {

    public List<Neuronio> neuronios;

    // inicializar os neuronios da camada
    public Camada(int entrada, int neuronios){

        this.neuronios = new ArrayList<Neuronio>();

        for (int i = 0; i < neuronios; i++) {
            this.neuronios.add(new Neuronio(entrada));
        }

    }

    public int getNumNeuronioCamada(){
        return this.neuronios.size();
    }
    
    public Neuronio getNeuronioCamada(int index){
        return this.neuronios.get(index);
    }

    public List<Neuronio> getNeuronios() {
        return neuronios;
    }

    public List<Double> getPesosNeuronios(int index) {
        List<Double> pesos = new ArrayList<Double>();

        for (int i = 0; i < neuronios.size(); i++) {
            pesos.add(neuronios.get(i).getPesos(index));
        }

        return pesos;
    }


   

}