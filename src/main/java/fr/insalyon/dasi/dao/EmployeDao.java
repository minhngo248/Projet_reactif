/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fr.insalyon.dasi.metier.modele.Dispo;

/**
 *
 * @author bbbbb
 */
public class EmployeDao {

    public void creer(Employe e) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(em);
    }
    
    public void modifier(Employe e) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(e);
    }

    public List<Employe> getListeEmployeEntreHoraire(Date heureDemande) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("Select e From Employe e where e.horaireDebut <= :heureDemande and e.horaireFin > :heureDemande and e.dispo = :dispo",
                Employe.class);
        query.setParameter("heureDemande", heureDemande);
        query.setParameter("dispo", Dispo.LIBRE);
        //Selectionner une liste d'employes qui sont libres et dans leurs horaires de travail 
        List<Employe> resultat = query.getResultList();
        return resultat;
    }

    public Employe chercherEmployeParMail(String unMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("Select e From Employe e where e.mail = :mail", Employe.class);
        query.setParameter("mail", unMail);
        List<Employe> uneListe = query.getResultList();
        Employe emp = null;
        if (!uneListe.isEmpty()) {
            emp = uneListe.get(0);
        }
        return emp;
    }

    public Employe rechercherEmployeParId(Long unId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Employe.class, unId); // renvoie null si l'identifiant n'existe pas
    }
}
