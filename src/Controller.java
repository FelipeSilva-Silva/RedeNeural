import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private TextField numNeuSaida;

    @FXML
    private TextField valorEntrada;

    @FXML
    private Button testar;

    @FXML
    private Label resultado;

    @FXML
    private TextField numCamadaInter;

    @FXML
    private TextField erro;

    @FXML
    private TextField epocas;

    @FXML
    private TextField taxaApren;

    @FXML
    private TextField numNeuEntrada;

    @FXML
    void realizarTeste(MouseEvent event) {
        try {
        	System.out.println("realizando teste");
            resultado.setText("testando controller");
            resultado.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
