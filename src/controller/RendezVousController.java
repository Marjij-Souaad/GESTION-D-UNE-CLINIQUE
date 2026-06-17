package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RendezVousController {

    @FXML
    private ComboBox<String> cbPatient;

    @FXML
    private DatePicker dateRdv;

    @FXML
    private TextField txtHeure;

    @FXML
    private TextArea txtMotif;

    @FXML
    private Slider sliderUrgence;

    @FXML
    private Spinner<Integer> spinnerSeances;

    @FXML
    private ComboBox<String> cbStatut;

    @FXML
    private TableView<?> tableRdv;

    @FXML
    private void ajouterRdv() {
        System.out.println("RDV ajouté");
    }

    @FXML
    private void modifierRdv() {
        System.out.println("RDV modifié");
    }

    @FXML
    private void supprimerRdv() {
        System.out.println("RDV supprimé");
    }
}