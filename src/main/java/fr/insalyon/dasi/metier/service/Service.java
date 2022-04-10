/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.service;

import com.google.maps.model.LatLng;
import java.util.*;
import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.InterventionDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Agence;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Dispo;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Genre;
import fr.insalyon.dasi.metier.modele.Intervention;
import fr.insalyon.dasi.metier.modele.Statut;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.GeoNetApi;
import util.Message;

/**
 *
 * @author lbezie
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();
    protected InterventionDao interventionDao = new InterventionDao();

    public void initialiserEmploye() throws ParseException {

        JpaUtil.creerContextePersistance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dt1 = "06/07/2001";
        String dt2 = "09/09/2000";
        String dt3 = "17/04/1990";
        String dt4 = "23/11/1995";
        String dt5 = "15/02/1989";
        String dt6 = "01/08/1993";

        SimpleDateFormat sdfHeure = new SimpleDateFormat("hh:mm:ss");
        String h1 = "08:00:00";
        String h2 = "13:00:00";
        String h3 = "15:00:00";
        String h4 = "20:00:00";

        Agence agence1 = new Agence("Agence Zola", "148 cours Emile Zola 69100 Villeurbanne");
        Agence agence2 = new Agence("Agence Croix-Luizet", "214 avenue Roger Salengro 69100 Villeurbanne");
        Agence agence3 = new Agence("Agence Tonkin", "3 place Jean Chorel 69100 Villeurbanne");

        Employe emp1 = new Employe(Genre.MME, "MARTIN", "Camille", "camille.martin@react.if", "345QSD", "0655447788", sdf.parse(dt1), sdfHeure.parse(h1), sdfHeure.parse(h2), agence1);
        Employe emp2 = new Employe(Genre.M, "WEBER", "Louis", "louis.weber@react.if", "789IOP", "0623568956", sdf.parse(dt2), sdfHeure.parse(h1), sdfHeure.parse(h3), agence1);

        Employe emp3 = new Employe(Genre.MME, "PALET", "Marie", "marie.palet@react.if", "654MLK", "0658234719", sdf.parse(dt3), sdfHeure.parse(h2), sdfHeure.parse(h4), agence2);
        Employe emp4 = new Employe(Genre.M, "CASSET", "Nicolas", "nicolas.casset@react.if", "963FGH", "0789542196", sdf.parse(dt4), sdfHeure.parse(h2), sdfHeure.parse(h4), agence2);

        Employe emp5 = new Employe(Genre.M, "JARRE", "Julien", "julien.jarre@react.if", "236BVC", "0745982030", sdf.parse(dt5), sdfHeure.parse(h1), sdfHeure.parse(h3), agence3);
        Employe emp6 = new Employe(Genre.M, "BAGUET", "Antoine", "antoine.baguet@react.if", "542GHJ", "0756140203", sdf.parse(dt6), sdfHeure.parse(h3), sdfHeure.parse(h4), agence3);

        try {
            JpaUtil.ouvrirTransaction();
            JpaUtil.obtenirContextePersistance().persist(emp1);
            JpaUtil.obtenirContextePersistance().persist(emp2);
            JpaUtil.obtenirContextePersistance().persist(emp3);
            JpaUtil.obtenirContextePersistance().persist(emp4);
            JpaUtil.obtenirContextePersistance().persist(emp5);
            JpaUtil.obtenirContextePersistance().persist(emp6);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }

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

            String corps = "Bonjour " + client.getPrenom() + ", nous vous confirmons votre inscription au service REACT'IF. Un cas d'urgence? "
                    + "Rendez-vous vite sur notre site, vous pouvez compter sur nous pour résoudre votre problème avec rapidité et efficacité.";
            Message.envoyerMail("contact@react.if", client.getMail(), "Bienvenue chez REACT'IF", corps);

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            String corps = "Bonjour " + client.getPrenom() + ", votre inscription au service REACT'IF amalencontreusement échoué...Merci derecommencer ultérieurement.";

            Message.envoyerMail("contact@react.if", client.getMail(), "Echec de l'inscription chez REACT'IF", corps);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client authentifierClient(String mail, String mdp) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherClientParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMdp().equals(mdp)) {
                    resultat = client;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,mdp)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Employe authentifierEmploye(String mail, String mdp) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Employe emp = employeDao.chercherEmployeParMail(mail);
            if (emp != null) {
                // Vérification du mot de passe
                if (emp.getMdp().equals(mdp)) {
                    resultat = emp;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierEmploye(mail,mdp)", ex);
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
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            c = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }

    public Employe rechercherEmployeParId(Long unId) {
        Employe e = null;
        JpaUtil.creerContextePersistance();
        try {
            e = employeDao.rechercherEmployeParId(unId);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            e = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return e;
    }

    public Employe demanderIntervention(Client client, Intervention intervention) throws Exception {
        JpaUtil.creerContextePersistance();
        Date instantCourant = new Date();
        List<Employe> listeEmp = employeDao.getListeEmployeEntreHoraire(instantCourant);
        Employe res = null;

        if (listeEmp.isEmpty()) {
            // aucun employé n'est dispo dans cette-heure ci

            JpaUtil.ouvrirTransaction();
            intervention.setClient(client);
            intervention.setStatut(Statut.REJET);
            client.addIntervention(intervention); //ajoute l'intervention à l'historique du client
            interventionDao.creer(intervention);
            clientDao.modifier(client);
            JpaUtil.validerTransaction();

            String corps = "Bonjour " + client.getPrenom() + ", votre demande d'intervention du " + intervention.afficherDateDemande() + " a été rejeté par manque de personnel disponible. "
                    + "Merci de recommencer ultérieurement. Veuillez nous excuser pour la gêne occasionée. ";
            Message.envoyerMail("contact@react.if", client.getMail(), "Rejet de demande d'intervention", corps);

            JpaUtil.fermerContextePersistance();

            return res;
        }

        LatLng latLngClient = new LatLng(client.getLatitude(), client.getLongitude());
        Double minTempsDeplacement = Double.MAX_VALUE;

        for (int i = 0; i < listeEmp.size(); ++i) {
            Employe e = listeEmp.get(i);
            LatLng latLngEmp = new LatLng(e.getLatitude(), e.getLongitude());

            if (minTempsDeplacement > GeoNetApi.getTripDurationByBicycleInMinute(latLngEmp, latLngClient)) {
                // prendre l'employé dont l'agence est le plus proche que l'adresse de client
                minTempsDeplacement = GeoNetApi.getTripDurationByBicycleInMinute(latLngEmp, latLngClient);
                res = e;
            }
        }

        try {
            JpaUtil.ouvrirTransaction();
            res.setDispo(Dispo.OCCUPE);

            intervention.setEmp(res);

            intervention.setClient(client);

            res.setInterventionEnCours(intervention);

            interventionDao.creer(intervention);
            client.addIntervention(intervention); //ajoute l'intervention à l'historique du client

            clientDao.modifier(client);
            employeDao.modifier(res);
            JpaUtil.validerTransaction();

            String corps = "Bonjour " + res.getPrenom() + ". Merci de prendre en charge l'intervention ' " + intervention.obtenirTypeIntervention()
                    + "' demander à " + intervention.afficherDateDemande() + " par " + intervention.getClient().getGenre() + " "
                    + intervention.getClient().getPrenom() + " " + intervention.getClient().getNom() + " " + intervention.getClient().getAddPostal() + ".";

            Message.envoyerNotification(res.getNumTel(), corps);

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return res;

    }

    public void cloturerIntervention(Intervention intervention, Statut unStatut, Date dateDeCloture, String cmt) throws Exception {

        if (intervention.getStatut() == null) {
        // Intervention est en cours de traitement
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            intervention.setStatut(unStatut);
            intervention.setDateDeCloture(dateDeCloture);
            intervention.setCommentaire(cmt);

            intervention.getEmp().setDispo(Dispo.LIBRE); //employe devient libre
            intervention.getEmp().addIntervention(intervention); //ajout de l'intervention à l'historique de l'intervention de l'employe
            intervention.getEmp().setInterventionEnCours(null);

            interventionDao.modifier(intervention); //on marque l'intervention comme clôturé
            employeDao.modifier(intervention.getEmp());
            JpaUtil.validerTransaction();
            JpaUtil.fermerContextePersistance();

            String mess;
            switch (unStatut) {
                case SUCCES:
                    mess = "Bonjour " + intervention.getClient().getPrenom() + ". Votre demande d'intervention du " + intervention.afficherDateDemande() + " a été clôturé  à "
                            + intervention.afficherDateDeCloture() + ". " + cmt + "Cordialement " + intervention.getEmp().getPrenom() + ".";
                    Message.envoyerNotification(intervention.getClient().getNumTel(), mess);
                    break;
                case ECHEC:

                    mess = "Bonjour " + intervention.getClient().getPrenom() + ". Votre demande d'intervention du " + intervention.afficherDateDemande() + " a échoué. " + cmt
                            + "Cordialement " + intervention.getEmp().getPrenom() + ".";
                    Message.envoyerNotification(intervention.getClient().getNumTel(), mess);
                    break;
            }
        }
    }

}
