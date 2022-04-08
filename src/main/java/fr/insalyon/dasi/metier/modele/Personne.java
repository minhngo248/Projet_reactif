/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;

/**
 *
 * @author lbezie
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Personne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String numTel;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;

    public Personne() {
    }

    public Personne( String nom, String prenom, String mail, String numTel, Date dateNaissance) {
     
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.numTel = numTel;
        this.dateNaissance = dateNaissance;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getNumTel() {
        return numTel;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

}
