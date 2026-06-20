package controller;

import dao.PatientDAO;
import dao.RendezVousDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Patient;
import model.RendezVous;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RendezVousController implements Initializable {

    @FXML private ComboBox<String> cbPatient;
    @FXML private DatePicker dateRdv;
    @FXML private TextField txtHeure;
    @FXML private TextArea txtMotif;
    @FXML private Slider sliderUrgence;
    @FXML private ComboBox<String> cbStatut;

    @FXML private TableView<RendezVous> tableRdv;
    @FXML private TableColumn<RendezVous, Integer> colId;
    @FXML private TableColumn<RendezVous, Integer> colPatientId;
    @FXML private TableColumn<RendezVous, LocalDate> colDate;
    @FXML private TableColumn<RendezVous, String> colHeure;
    @FXML private TableColumn<RendezVous, String> colMotif;
    @FXML private TableColumn<RendezVous, Integer> colUrgence;
    @FXML private TableColumn<RendezVous, String> colStatut;

    private RendezVousDAO rdvDAO;
    private PatientDAO patientDAO;
    private ObservableList<RendezVous> rdvList;
    private RendezVous selectedRdv = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rdvDAO = new RendezVousDAO();
        patientDAO = new PatientDAO();

        if (dao.Database.getConnection() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText("Base de données inaccessible");
            alert.setContentText("Impossible de se connecter à MySQL.\nVeuillez vous assurer que WampServer (MySQL) est démarré et que la base de données 'gestion_clinique' existe.");
            alert.show();
        }

        cbStatut.setItems(FXCollections.observableArrayList("Prévu", "Terminé", "Annulé"));
        loadPatients();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateRdv"));
        colHeure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        colMotif.setCellValueFactory(new PropertyValueFactory<>("motif"));
        colUrgence.setCellValueFactory(new PropertyValueFactory<>("urgence"));
        colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));

        loadTableData();

        tableRdv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedRdv = newSelection;
                fillFields(selectedRdv);
            }
        });
    }

    private void loadPatients() {
        ObservableList<String> patients = FXCollections.observableArrayList();
        for (Patient p : patientDAO.getAll()) {
            patients.add(p.getId() + " - " + p.getNom() + " " + p.getPrenom());
        }
        cbPatient.setItems(patients);
    }

    private void loadTableData() {
        rdvList = FXCollections.observableArrayList(rdvDAO.getAll());
        tableRdv.setItems(rdvList);
    }

    private void fillFields(RendezVous r) {
        // Find matching patient in combobox
        for (String pStr : cbPatient.getItems()) {
            if (pStr.startsWith(r.getPatientId() + " - ")) {
                cbPatient.setValue(pStr);
                break;
            }
        }
        dateRdv.setValue(r.getDateRdv());
        txtHeure.setText(r.getHeure());
        txtMotif.setText(r.getMotif());
        sliderUrgence.setValue(r.getUrgence());
        cbStatut.setValue(r.getStatut());
    }

    private int extractPatientId() {
        if (cbPatient.getValue() != null) {
            String val = cbPatient.getValue();
            String idStr = val.split(" - ")[0];
            return Integer.parseInt(idStr);
        }
        return -1;
    }

    @FXML
    public void ajouterRdv() {
        if (isInputValid()) {
            RendezVous r = new RendezVous(0, extractPatientId(), dateRdv.getValue(), txtHeure.getText(), txtMotif.getText(), (int) sliderUrgence.getValue(), cbStatut.getValue());
            rdvDAO.ajouter(r);
            loadTableData();
            clearFields();
        }
    }

    @FXML
    public void modifierRdv() {
        if (selectedRdv != null && isInputValid()) {
            selectedRdv.setPatientId(extractPatientId());
            selectedRdv.setDateRdv(dateRdv.getValue());
            selectedRdv.setHeure(txtHeure.getText());
            selectedRdv.setMotif(txtMotif.getText());
            selectedRdv.setUrgence((int) sliderUrgence.getValue());
            selectedRdv.setStatut(cbStatut.getValue());
            
            rdvDAO.modifier(selectedRdv);
            loadTableData();
            clearFields();
        }
    }

    @FXML
    public void supprimerRdv() {
        if (selectedRdv != null) {
            rdvDAO.supprimer(selectedRdv.getId());
            loadTableData();
            clearFields();
        }
    }

    @FXML
    public void clearFields() {
        selectedRdv = null;
        cbPatient.getSelectionModel().clearSelection();
        dateRdv.setValue(null);
        txtHeure.clear();
        txtMotif.clear();
        sliderUrgence.setValue(1);
        cbStatut.getSelectionModel().clearSelection();
        tableRdv.getSelectionModel().clearSelection();
    }

    private boolean isInputValid() {
        return cbPatient.getValue() != null && dateRdv.getValue() != null && !txtHeure.getText().isEmpty() && cbStatut.getValue() != null;
    }
}