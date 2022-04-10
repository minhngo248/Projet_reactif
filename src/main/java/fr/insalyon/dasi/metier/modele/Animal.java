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
public class Animal extends Intervention {

    private String animal;

    public Animal() {
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getAnimal() {
        return animal;
    }

    public Animal(String animal, String description, Date dateDemande, Client client) {
        super(description, dateDemande, client);
        this.animal = animal;
    }

    @Override
    public String obtenirTypeIntervention() {
        return "Animal";
    }
}
