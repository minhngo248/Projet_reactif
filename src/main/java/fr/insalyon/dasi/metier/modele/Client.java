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
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author lbezie
 */
@Entity
public class Client extends Personne {

    private String mdp;
    private String addPostal;
    @OneToMany(mappedBy = "client")
    private List<Intervention> historique;
    private Double latitude;
    private Double longitude;

    public Client() {

    }

    public Client(Genre genre, String nom, String prenom, String mail, String mdp, String addPostal, String numTel, Date dateNaissance) {
        super(genre, nom, prenom, mail, numTel, dateNaissance);
        this.historique = new ArrayList<>();
        this.addPostal = addPostal;
        this.mdp = mdp;
    }

    public void setHistorique(List<Intervention> historique) {
        this.historique = historique;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getAddPostal() {
        return addPostal;
    }

    public String getMdp() {
        return mdp;
    }

    public void setAddPostal(String addPostal) {
        this.addPostal = addPostal;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setCoordoneesGPS(LatLng latLng) {
        this.latitude = latLng.lat;
        this.longitude = latLng.lng;
    }

    public List<Intervention> getHistorique() {
        if (historique.isEmpty()) {
            System.out.println(this.getPrenom() + " " + this.getNom() + " n'a fait aucune demande d'intervention.");

        } else {
            System.out.println("L'historique des interventions demandées par " + this.getPrenom() + " " + this.getNom() + " : ");
        }
        return historique;
    }

    public void addIntervention(Intervention intervention) {
        historique.add(intervention);
    }

}
