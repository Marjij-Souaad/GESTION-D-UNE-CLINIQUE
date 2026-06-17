package dao;

import model.Patient;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void ajouter(Patient p) {
        String sql = "INSERT INTO patients (nom, prenom, sexe, date_naissance, telephone, adresse) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getSexe());
            ps.setDate(4, Date.valueOf(p.getDateNaissance()));
            ps.setString(5, p.getTelephone());
            ps.setString(6, p.getAdresse());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur ajout patient : " + e.getMessage());
        }
    }

    public List<Patient> getAll() {
        List<Patient> liste = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Statement st = Database.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Patient p = new Patient();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setSexe(rs.getString("sexe"));
                p.setDateNaissance(rs.getDate("date_naissance").toLocalDate());
                p.setTelephone(rs.getString("telephone"));
                p.setAdresse(rs.getString("adresse"));
                liste.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erreur liste patients : " + e.getMessage());
        }
        return liste;
    }

    public void modifier(Patient p) {
        String sql = "UPDATE patients SET nom=?, prenom=?, sexe=?, date_naissance=?, telephone=?, adresse=? WHERE id=?";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getSexe());
            ps.setDate(4, Date.valueOf(p.getDateNaissance()));
            ps.setString(5, p.getTelephone());
            ps.setString(6, p.getAdresse());
            ps.setInt(7, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur modification patient : " + e.getMessage());
        }
    }

    public void supprimer(int id) {
        String sql = "DELETE FROM patients WHERE id=?";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur suppression patient : " + e.getMessage());
        }
    }
}
