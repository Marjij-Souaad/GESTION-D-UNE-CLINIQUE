package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PatientController {

    @FXML private TextField nom;
    @FXML private TextField prenom;

    @FXML
    private void addPatient() {
        System.out.println("Ajout: " + nom.getText());
    }

    @FXML
    private void updatePatient() {
        System.out.println("Modification");
    }

    @FXML
    private void deletePatient() {
        System.out.println("Suppression");
    }
}