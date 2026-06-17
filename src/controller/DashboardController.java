package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class DashboardController {

    @FXML
    private Label lblPatients;

    @FXML
    private Label lblRdv;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private void initialize() {

        lblPatients.setText("Total Patients : 0");
        lblRdv.setText("Total Rendez-vous : 0");
    }

    @FXML
    private void changeColor() {

        Color color = colorPicker.getValue();

        System.out.println("Couleur choisie: " + color);
    }
}