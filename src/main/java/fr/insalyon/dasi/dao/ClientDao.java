/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Intervention;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author bbbbb
 */
public class ClientDao {

    public void creer(Client client) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(client);
    }

    public void modifier(Client client) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(client);
    }

    public Client rechercheClient(Long unId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Client.class, unId); // renvoie null si l'identifiant n'existe pas
    }

    public List<Client> listerClients() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c ORDER BY c.nom ASC, c.prenom ASC",
                Client.class);
        return query.getResultList();
    }

    public Client chercherClientParMail(String unMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery("Select c From Client c where c.mail = :mail", Client.class);
        query.setParameter("mail", unMail);
        List<Client> uneListe = query.getResultList();
        Client c = null;
        if (!uneListe.isEmpty()) {
            c = uneListe.get(0);
        }
        return c;
    }

    public void supprimerClient(Long unId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Client client = em.find(Client.class, unId);
        em.remove(client);
    }

    public List<Intervention> listerDemandeIntervention(Long idClient) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Intervention> query;
        query = em.createQuery("SELECT i FROM Intervention i WHERE i.client_id= :id",
                Intervention.class);
        query.setParameter("id", idClient);
        return query.getResultList();
    }
}
