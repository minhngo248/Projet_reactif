/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import com.google.maps.model.LatLng;
import util.GeoNetApi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author lbezie
 */
@Entity
public class Agence implements Serializable {

    @Column(unique = true)
    private String nom;

    private String adresse;

    @OneToMany(mappedBy = "agence",
            cascade={CascadeType.PERSIST},
            fetch=FetchType.EAGER)
    private List<Employe> listeEmp = new ArrayList<>();
    private Double latitude;
    private Double longitude;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Agence() {
    }

    public Agence(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
        LatLng latLng = GeoNetApi.getLatLng(adresse);
        this.latitude = latLng.lat;
        this.longitude = latLng.lng;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Long getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setListeEmp(List<Employe> listeEmp) {
        this.listeEmp = listeEmp;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<Employe> getListeEmp() {
        return listeEmp;
    }

}
