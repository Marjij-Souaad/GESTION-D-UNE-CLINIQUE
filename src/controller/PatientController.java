package controller;

import dao.PatientDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Patient;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML private TextField txtNom;
    @FXML private TextField txtPrenom;
    @FXML private ComboBox<String> cbSexe;
    @FXML private DatePicker dpDateNaissance;
    @FXML private TextField txtTelephone;
    @FXML private TextField txtAdresse;

    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, Integer> colId;
    @FXML private TableColumn<Patient, String> colNom;
    @FXML private TableColumn<Patient, String> colPrenom;
    @FXML private TableColumn<Patient, String> colSexe;
    @FXML private TableColumn<Patient, LocalDate> colDateNaissance;
    @FXML private TableColumn<Patient, String> colTelephone;
    @FXML private TableColumn<Patient, String> colAdresse;

    private PatientDAO patientDAO;
    private ObservableList<Patient> patientList;
    private Patient selectedPatient = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patientDAO = new PatientDAO();
        
        if (dao.Database.getConnection() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText("Base de données inaccessible");
            alert.setContentText("Impossible de se connecter à MySQL.\nVeuillez vous assurer que WampServer (MySQL) est démarré et que la base de données 'gestion_clinique' existe.");
            alert.show();
        }
        
        cbSexe.setItems(FXCollections.observableArrayList("M", "F"));

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        colDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        loadTableData();

        tablePatients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedPatient = newSelection;
                fillFields(selectedPatient);
            }
        });
    }

    private void loadTableData() {
        patientList = FXCollections.observableArrayList(patientDAO.getAll());
        tablePatients.setItems(patientList);
    }

    private void fillFields(Patient p) {
        txtNom.setText(p.getNom());
        txtPrenom.setText(p.getPrenom());
        cbSexe.setValue(p.getSexe());
        dpDateNaissance.setValue(p.getDateNaissance());
        txtTelephone.setText(p.getTelephone());
        txtAdresse.setText(p.getAdresse());
    }

    @FXML
    public void addPatient() {
        if (isInputValid()) {
            Patient p = new Patient(0, txtNom.getText(), txtPrenom.getText(), cbSexe.getValue(), dpDateNaissance.getValue(), txtTelephone.getText(), txtAdresse.getText());
            patientDAO.ajouter(p);
            loadTableData();
            clearFields();
        }
    }

    @FXML
    public void updatePatient() {
        if (selectedPatient != null && isInputValid()) {
            selectedPatient.setNom(txtNom.getText());
            selectedPatient.setPrenom(txtPrenom.getText());
            selectedPatient.setSexe(cbSexe.getValue());
            selectedPatient.setDateNaissance(dpDateNaissance.getValue());
            selectedPatient.setTelephone(txtTelephone.getText());
            selectedPatient.setAdresse(txtAdresse.getText());
            
            patientDAO.modifier(selectedPatient);
            loadTableData();
            clearFields();
        }
    }

    @FXML
    public void deletePatient() {
        if (selectedPatient != null) {
            patientDAO.supprimer(selectedPatient.getId());
            loadTableData();
            clearFields();
        }
    }

    @FXML
    public void clearFields() {
        selectedPatient = null;
        txtNom.clear();
        txtPrenom.clear();
        cbSexe.getSelectionModel().clearSelection();
        dpDateNaissance.setValue(null);
        txtTelephone.clear();
        txtAdresse.clear();
        tablePatients.getSelectionModel().clearSelection();
    }

    private boolean isInputValid() {
        return !txtNom.getText().isEmpty() && !txtPrenom.getText().isEmpty() && cbSexe.getValue() != null && dpDateNaissance.getValue() != null;
    }
}