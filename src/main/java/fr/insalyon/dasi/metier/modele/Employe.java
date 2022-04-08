/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author lbezie
 */
@Entity
public class Employe extends Personne {

    private String mdp;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date horaireDebut;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date horaireFin;
    
    @ManyToOne
    private Agence agence;

    @OneToMany(mappedBy = "emp")
    private List<Intervention> listeIntervention = new ArrayList<>();

    public Employe() {
    }

    public Employe(String mdp, Date horaireDebut, Date horaireFin, Agence agence, String nom, String prenom, String mail, String numTel, Date dateNaissance) {
        super(nom, prenom, mail, numTel, dateNaissance);
        this.mdp = mdp;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.agence = agence;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

}
