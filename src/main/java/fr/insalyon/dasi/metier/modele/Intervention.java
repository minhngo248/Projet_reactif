/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Intervention implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDemande;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDeCloture;
    private String commentaire;
    private Statut statut;
    @ManyToOne 
    Employe emp;
    @ManyToOne 
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

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
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
        return "Intervention{" + "description=" + description + ", dateDemande=" + dateDemande + ", dateDeCloture=" + dateDeCloture + ", commentaire=" + commentaire + ", statut=" + statut + ", emp=" + emp + '}';
    }
    
    
}
