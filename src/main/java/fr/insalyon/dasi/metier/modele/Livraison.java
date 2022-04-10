/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author bbbbb
 */
@Entity
public class Livraison extends Intervention {

    private String objet;
    private String entreprise;

    public Livraison() {
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getObjet() {
        return objet;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public Livraison(String objet, String entreprise, String description, Date dateDemande, Client client) {
        super(description, dateDemande, client);
        this.objet = objet;
        this.entreprise = entreprise;
    }

    public String obtenirTypeIntervention() {
        return "Livraison";
    }

}
