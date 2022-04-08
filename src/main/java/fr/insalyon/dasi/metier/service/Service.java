/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.service;

import java.util.*;
import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Intervention;
import fr.insalyon.dasi.metier.modele.Statut;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import org.joda.time.LocalDate;
import util.GeoNetApi;
import util.Message;

/**
 *
 * @author lbezie
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();

    public Long inscrireClient(Client client) {

        Long resultat = null;
        JpaUtil.creerContextePersistance();

        try {
            //enregistre les coordonnnées GPS du Client
            client.setCoordoneesGPS(GeoNetApi.getLatLng(client.getAddPostal()));
            JpaUtil.ouvrirTransaction();

            clientDao.creer(client);
            JpaUtil.validerTransaction();
            resultat = client.getId();

            String corps = "Bonjour" + client.getPrenom() + ", nous vous confirmons votre inscription au service REACT'IF. Un cas d'urgence? "
                    + "Rendez-vous vite sur notre site, vous pouvez compter sur nous pour résoudre votre problème avec rapidité et efficacité.";
            Message.envoyerMail("contact@react.if", client.getMail(), "Bienvenue chez REACT'IF", corps);

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            JpaUtil.annulerTransaction();
            String corps = "Bonjour" + client.getPrenom() + ", votre inscription au service REACT'IF amalencontreusement échoué...Merci derecommencer ultérieurement.";

            Message.envoyerMail("contact@react.if", client.getMail(), "Echec de l'inscription chez REACT'IF", corps);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Client rechercherClientParId(Long unId) {
        Client c = null;
        JpaUtil.creerContextePersistance();
        try {
            c = clientDao.rechercheClient(unId);
        } catch(Exception ex) {
            ex.printStackTrace(System.out);
            c = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }
    
    public Employe demanderIntervention(Client client, Intervention intervention) {
        intervention.setDateDemande(Date.from(Instant.now()));
        List<Employe> listeEmp = employeDao.getListeEmployeEntreHoraire(LocalTime.now());
        String mess;
        Employe res = null;
        if (listeEmp == null) {
            mess = "Il n'y a pas d'employé maintenant.";
            System.out.println(mess);
            intervention.setStatut(Statut.REJET);
        } else {
            res = listeEmp.get(0);
            intervention.setEmp(listeEmp.get(0));
            intervention.setClient(client);
        }
        return res;
    }
    
    public void cloturerIntervention(Intervention intervention, Statut unStatut, Date dateDeCloture, String cmt) {
        if (intervention.getStatut() == null) {
            intervention.setStatut(unStatut);
            intervention.setDateDeCloture(dateDeCloture);
            intervention.setCommentaire(cmt);
            String mess;
            switch (unStatut) {
                case SUCCES:
                    mess = "Succes";
                    Message.envoyerNotification(intervention.getClient().getNumTel(), mess);
                    break;
                case ECHEC:
                    mess = "Echec";
                    Message.envoyerNotification(intervention.getClient().getNumTel(), mess);
                    break;
            }
        }
    }
    
}
