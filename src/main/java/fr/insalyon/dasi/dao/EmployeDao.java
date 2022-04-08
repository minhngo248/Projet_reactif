/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import java.time.*;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author bbbbb
 */
public class EmployeDao {
    
    public void creer(Employe e) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(em);
    }
    
    public List<Employe> getListeEmployeEntreHoraire(Date heureDemande) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("Select e From Employe e where e.horaireDebut <= :heureDemande and e.horaireFin > :heureDemande", 
                Employe.class);
        query.setParameter("heureDemande", heureDemande);
        List<Employe> resultat = query.getResultList();
        return resultat;
    }
}
