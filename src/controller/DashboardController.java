package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label lblPatients;

    @FXML
    public void initialize() {
        if (lblPatients != null) {
            lblPatients.setText("0");
        }
    }
}