/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Intervention;
import javax.persistence.EntityManager;

/**
 *
 * @author bbbbb
 */
public class InterventionDao {

    public void creer(Intervention i) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(i);
    }

    public void modifier(Intervention i) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(i);
    }

    public Intervention rechercheInterventionParId(Long unId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Intervention.class, unId); // renvoie null si l'identifiant n'existe pas
    }
}
