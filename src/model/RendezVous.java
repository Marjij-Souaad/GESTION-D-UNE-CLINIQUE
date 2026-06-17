package model;

import java.time.LocalDate;

public class RendezVous {

    private int id;
    private int patientId;
    private LocalDate dateRdv;
    private String heure;
    private String motif;
    private int urgence;
    private String statut;

    public RendezVous() {
    }

    public RendezVous(int id,
                      int patientId,
                      LocalDate dateRdv,
                      String heure,
                      String motif,
                      int urgence,
                      String statut) {

        this.id = id;
        this.patientId = patientId;
        this.dateRdv = dateRdv;
        this.heure = heure;
        this.motif = motif;
        this.urgence = urgence;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public LocalDate getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(LocalDate dateRdv) {
        this.dateRdv = dateRdv;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getUrgence() {
        return urgence;
    }

    public void setUrgence(int urgence) {
        this.urgence = urgence;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}