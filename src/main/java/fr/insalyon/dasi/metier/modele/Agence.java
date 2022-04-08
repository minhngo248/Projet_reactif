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
import javax.persistence.Column;
import javax.persistence.Entity;
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
    
    @OneToMany(mappedBy = "agence")
    private List<Employe> listeEmp = new ArrayList<>();
    private Double latitude;
    private Double longitude;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
