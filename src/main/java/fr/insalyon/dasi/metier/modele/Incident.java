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
 * @author lbezie
 */
@Entity
public class Incident extends Intervention {

    public Incident() {
    }

    public Incident(String description, Date dateDemande, Client client) {
        super(description, dateDemande, client);
    }
    
}
