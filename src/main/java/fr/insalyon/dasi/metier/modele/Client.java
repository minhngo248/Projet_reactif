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

    private String addPostal;
    private String mdp;
    @OneToMany(mappedBy = "client")
    private List<Intervention> historique = new ArrayList<>();
    private Double latitude;
    private Double longitude;

    public Client() {
    }

    public Client(String nom, String prenom, String mail, String mdp, String addPostal, String numTel, Date dateNaissance) {
        super(nom, prenom, mail, numTel, dateNaissance);
        this.addPostal = addPostal;
        this.mdp = mdp;
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
        return historique;
    }
    
    public void addIntervention(Intervention intervention){
        historique.add(intervention);
    }

}
