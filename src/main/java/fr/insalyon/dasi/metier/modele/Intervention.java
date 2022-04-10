/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author lbezie
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Intervention implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDemande;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDeCloture;
    
    private String commentaire;
    
    @Enumerated(STRING)
    private Statut statut;
    
    @ManyToOne(fetch=FetchType.EAGER)
    Employe emp;
    @ManyToOne(fetch=FetchType.EAGER)
    Client client;

    public Intervention() {
    }

    public Intervention(String description, Date dateDemande, Client client) {
        this.description = description;
        this.dateDemande = dateDemande;
        this.client = client;
        this.statut = null;
        this.commentaire = null;
        this.dateDeCloture = null;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Date getDateDeCloture() {
        return dateDeCloture;
    }

    public void setDateDeCloture(Date dateDeCloture) {
        this.dateDeCloture = dateDeCloture;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public String afficherDateDemande() {
        int month = dateDemande.getMonth() + 1;
        int year = dateDemande.getYear() + 1900;
        String msg = dateDemande.getDate() + "/" + month + "/" + year + " à "
                + dateDemande.getHours() + "h" + dateDemande.getMinutes();
        return msg;
    }

    public String afficherDateDeCloture() {
        String msg = null;
        int month = dateDemande.getMonth() + 1;
        int year = dateDemande.getYear() + 1900;
        if (dateDeCloture != null) {
            msg = dateDeCloture.getDate() + "/" + month + "/" + year + " à "
                    + dateDeCloture.getHours() + "h" + dateDeCloture.getMinutes();
        }
        return msg;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Employe getEmp() {
        return emp;
    }

    public void setEmp(Employe emp) {
        this.emp = emp;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Intervention: " + this.obtenirTypeIntervention()
                + " description= " + description + ", dateDemande= " + this.afficherDateDemande() + ", dateDeCloture= " + this.afficherDateDeCloture() + ", commentaire= " + commentaire + ", statut= " + statut + ", emp= " + emp.getPrenom() + " " + emp.getNom() + " }\n";
    }

    public String obtenirTypeIntervention() {
        return null;
    }

}
