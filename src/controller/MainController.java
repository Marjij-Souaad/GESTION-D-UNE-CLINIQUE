package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    public void initialize() {
        System.out.println("MainController Initialized");
        openPatients();
    }

    @FXML
    public void openPatients() {
        load("patient.fxml");
    }

    @FXML
    public void openRendezVous() {
        load("rendezvous.fxml");
    }

    private void load(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + fxml));
            Parent root = loader.load();
            mainPane.setCenter(root);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de la vue: " + fxml);
            e.printStackTrace();
        }
    }
}