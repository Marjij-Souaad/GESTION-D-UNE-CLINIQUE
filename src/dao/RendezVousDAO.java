package dao;

import model.RendezVous;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO {

    public void ajouter(RendezVous r) {
        String sql = "INSERT INTO rendezvous (patient_id, date_rdv, heure, motif, urgence, statut) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setInt(1, r.getPatientId());
            ps.setDate(2, Date.valueOf(r.getDateRdv()));
            ps.setString(3, r.getHeure());
            ps.setString(4, r.getMotif());
            ps.setInt(5, r.getUrgence());
            ps.setString(6, r.getStatut());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur ajout RDV : " + e.getMessage());
        }
    }

    public List<RendezVous> getAll() {
        List<RendezVous> liste = new ArrayList<>();
        String sql = "SELECT * FROM rendezvous";
        try (Statement st = Database.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                RendezVous r = new RendezVous();
                r.setId(rs.getInt("id"));
                r.setPatientId(rs.getInt("patient_id"));
                r.setDateRdv(rs.getDate("date_rdv").toLocalDate());
                r.setHeure(rs.getString("heure"));
                r.setMotif(rs.getString("motif"));
                r.setUrgence(rs.getInt("urgence"));
                r.setStatut(rs.getString("statut"));
                liste.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Erreur liste RDV : " + e.getMessage());
        }
        return liste;
    }

    public void modifier(RendezVous r) {
        String sql = "UPDATE rendezvous SET patient_id=?, date_rdv=?, heure=?, motif=?, urgence=?, statut=? WHERE id=?";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setInt(1, r.getPatientId());
            ps.setDate(2, Date.valueOf(r.getDateRdv()));
            ps.setString(3, r.getHeure());
            ps.setString(4, r.getMotif());
            ps.setInt(5, r.getUrgence());
            ps.setString(6, r.getStatut());
            ps.setInt(7, r.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur modification RDV : " + e.getMessage());
        }
    }

    public void supprimer(int id) {
        String sql = "DELETE FROM rendezvous WHERE id=?";
        try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur suppression RDV : " + e.getMessage());
        }
    }
}
