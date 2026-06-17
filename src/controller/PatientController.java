package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PatientController {

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtTelephone;

    @FXML
    private TextArea txtAdresse;

    @FXML
    private DatePicker dateNaissance;

    @FXML
    private RadioButton rbHomme;

    @FXML
    private RadioButton rbFemme;

    @FXML
    private TableView<?> tablePatients;

    @FXML
    private void ajouterPatient() {

        System.out.println("Patient ajouté");
    }

    @FXML
    private void modifierPatient() {

        System.out.println("Patient modifié");
    }

    @FXML
    private void supprimerPatient() {

        System.out.println("Patient supprimé");
    }

    @FXML
    private void viderChamps() {

        txtNom.clear();
        txtPrenom.clear();
        txtTelephone.clear();
        txtAdresse.clear();
    }
}