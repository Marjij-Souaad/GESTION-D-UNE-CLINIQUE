package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label message;

    @FXML
    private void login() {

        if ("admin".equals(username.getText()) &&
            "123".equals(password.getText())) {

            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/view/MainView.fxml")
                );

                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) username.getScene().getWindow();

                stage.setScene(scene);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            message.setText("Login incorrect");
        }
    }
}