/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import com.google.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import util.GeoNetApi;

/**
 *
 * @author lbezie
 */
@Entity
public class Employe extends Personne {

    private String mdp;

    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaireDebut;

    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaireFin;

    @ManyToOne(cascade={CascadeType.PERSIST},
            fetch=FetchType.EAGER)
    private Agence agence;

    @OneToMany(mappedBy = "emp")
    private List<Intervention> listeIntervention;

    @Column(columnDefinition="character varying (100) not null")
    private boolean dispo;

    private Double distanceCumule;

    @OneToOne(fetch=FetchType.EAGER)
    private Intervention interventionEnCours;

    public Employe() {
    }

    public Employe(Genre genre, String nom, String prenom, String mail, String mdp, String numTel, Date dateNaissance, Date horaireDebut, Date horaireFin, Agence agence) {
        super(genre, nom, prenom, mail, numTel, dateNaissance);
        this.mdp = mdp;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.agence = agence;
        this.listeIntervention = new ArrayList<>();
        this.dispo = true;
        this.distanceCumule = 0.0;
        this.interventionEnCours = null;
    }

    public boolean getDispo() {
        return dispo;
    }

    public Intervention getInterventionEnCours() {

        return interventionEnCours;
    }

    public void setDistanceCumule(double distanceCumule) {
        this.distanceCumule = distanceCumule;
    }

    public void setInterventionEnCours(Intervention interventionEnCours) {
        this.interventionEnCours = interventionEnCours;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Date getHoraireDebut() {
        return horaireDebut;
    }

    public void setHoraireDebut(Date horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    public Date getHoraireFin() {
        return horaireFin;
    }

    public void setHoraireFin(Date horaireFin) {
        this.horaireFin = horaireFin;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public List<Intervention> getListeIntervention() {
        return listeIntervention;
    }

    public void setListeIntervention(List<Intervention> listeIntervention) {
        this.listeIntervention = listeIntervention;
    }

    public double getLatitude() {
        return this.agence.getLatitude();
    }

    public double getLongitude() {
        return this.agence.getLongitude();
    }

    public void addIntervention(Intervention intervention) {
        listeIntervention.add(intervention);
        LatLng latLngEmp = new LatLng(this.getLatitude(), this.getLongitude());
        LatLng latLngClient = new LatLng(intervention.getClient().getLatitude(), intervention.getClient().getLongitude());
        this.distanceCumule = this.distanceCumule + GeoNetApi.getTripDistanceByCarInKm(latLngEmp, latLngClient);
    }

    public double getDistanceCumule() {
        return distanceCumule;
    }

}
