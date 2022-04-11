/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Agence;

import fr.insalyon.dasi.metier.modele.Animal;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Genre;
import fr.insalyon.dasi.metier.modele.Incident;
import fr.insalyon.dasi.metier.modele.Intervention;
import fr.insalyon.dasi.metier.modele.Livraison;
import fr.insalyon.dasi.metier.modele.Statut;

import fr.insalyon.dasi.metier.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bbbbb
 */
public class Main {

    public static void main(String[] args) throws ParseException, Exception {
        JpaUtil.init();

        /*Initialisation*/
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dt = "01/06/2001";
        String dtBis = "03/08/1994";
        Client client = new Client(Genre.MME, "PASCAL", "Alice", "alice.pascal@free.fr", "123AZE", "10 avenue Albert Einstein 69100 Villeurbanne", "06 88 77 44 55", sdf.parse(dt));
        Client clientBis = new Client(Genre.M, "FAROUK", "Henri", "henri.farouk@gmail.com", "987KLM", "100 avenue Roger Salengro 69100 Villeurbanne", "07 11 22 33 44", sdf.parse(dtBis));

        testerInscriptionClient(client);
        testerInscriptionClient(clientBis);
        testerInitialiserEmploye();

        /*Authentification*/
//        String mail;
//        String motDePasse;
//
//        mail = "alice.pascal@free.fr";
//        motDePasse = "Minh123";
//        testerAuthentifierClient(mail, motDePasse);
//
//        mail = "alice.pascal@free.fr";
//        motDePasse = "123AZE";
//        testerAuthentifierClient(mail, motDePasse);
//
//        mail = "camille.martin@react.if";
//        motDePasse = "123QSD";
//        testerAuthentifierEmploye(mail, motDePasse);
//
//        mail = "camille.martin@react.if";
//        motDePasse = "345QSD";
//        testerAuthentifierEmploye(mail, motDePasse);

        /*Demande intervention*/
        Date instant = new Date();
        Livraison livraison = new Livraison("un canapé", "JOLISMEUBLES SA", "Merci de réceptionner mon canapé et de le faire déposer dans le salon", instant, client);
        Animal animal = new Animal("chien", "il faudrait promener Médor 30 min dans le jardin en face de l'immeuble", new Date(), client);
        Incident incident = new Incident("une fuite d'eau a été signalée par le voisin du dessous", new Date(), client);
        testerDemanderIntervention(client, livraison);
        testerDemanderIntervention(client, animal);
        testerDemanderIntervention(client, incident);

        /*Cloturer une intervention*/
        String cmt = "Votre canapé vous attends dans votre salon. J'ai déposé"
                + " le bon de livraison sur la table de cuisine. ";
        testerCloturerIntervention(livraison, Statut.SUCCES, cmt);

        cmt = "Je n'ai rien pu faire. Votre appartement a été inondé.";
        testerCloturerIntervention(incident, Statut.ECHEC, cmt);

        /*Historique des interventions du client*/
//        long unIdClient = Long.valueOf(1);
//        testerConsulterHistoriqueDemandeInterventionClient(unIdClient);
//
//        unIdClient = Long.valueOf(2);
//        testerConsulterHistoriqueDemandeInterventionClient(unIdClient);

        /*Historique des interventions de l'employé*/
//        long unIdEmp = Long.valueOf(4);
//        testerConsulterHistoriqueInterventionEmploye(unIdEmp);
//
//        unIdEmp = Long.valueOf(8);
//        testerConsulterHistoriqueInterventionEmploye(unIdEmp);


        /*Intervention en cours d'un employé*/
//        Long unIdEmp = Long.valueOf(6);
//        testerConsulterInterventionEnCours(unIdEmp);
//
//        unIdEmp = Long.valueOf(8);
//        testerConsulterInterventionEnCours(unIdEmp);
        JpaUtil.destroy();
    }

    public static void testerInscriptionClient(Client client) throws ParseException {

        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();

        Service service = new Service();

        Long idClient = service.inscrireClient(client);
        if (idClient != null) {
            System.out.println("> Succès lors de l'appel au service InscriptionClient");
        } else {
            System.out.println("> Échec lors de l'appel au service InscriptionClient");
        }
    }

    public static void testerInitialiserEmploye() throws ParseException {
        System.out.println();
        System.out.println("**** initialiserEmploye() ****");
        System.out.println();
        Service service = new Service();

        service.initialiserEmploye();

    }

    public static void testerAuthentifierClient(String mail, String motDePasse) {

        System.out.println();
        System.out.println("**** testerAuthentificationClient() ****");
        System.out.println();

        Service service = new Service();
        Client client;
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
        System.out.println();

        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
        System.out.println();
    }

    public static void testerAuthentifierEmploye(String mail, String motDePasse) {

        System.out.println();
        System.out.println("**** testerAuthentificationEmploye() ****");
        System.out.println();

        Service service = new Service();
        Employe emp;

        emp = service.authentifierEmploye(mail, motDePasse);
        if (emp != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
        System.out.println();

    }

    public static void testerDemanderIntervention(Client client, Intervention intervention) throws ParseException, Exception {
        System.out.println();
        System.out.println("**** testerDemanderIntervention() ****");
        System.out.println();

        Service service = new Service();
        Employe e = service.demanderIntervention(client, intervention);
        if (e == null) {
            System.out.println("Veuillez demander ultérieurement. Erreur de demander l'intervention.");
        }
    }

    public static void testerCloturerIntervention(Intervention i, Statut statut, String commentaire) throws Exception {
        System.out.println();
        System.out.println("**** testerCloturerIntervention() ****");
        System.out.println();
        Service service = new Service();
        Date dateDeCloture = new Date();
        service.cloturerIntervention(i, statut, dateDeCloture, commentaire);
    }

    public static void testerConsulterHistoriqueDemandeInterventionClient(Long unId) throws Exception {
        System.out.println();
        System.out.println("**** testerConsulterHistoriqueDemandeInterventionClient() ****");
        System.out.println();

        Service service = new Service();
        Client client = service.rechercherClientParId(unId);

        //client.getHistorique().size();
        System.out.println(client.getHistorique());

    }

    public static void testerConsulterHistoriqueInterventionEmploye(Long unId) {
        System.out.println();
        System.out.println("**** testerConsulterHistoriqueInterventionEmploye() ****");
        System.out.println();

        Service service = new Service();
        Employe e = service.rechercherEmployeParId(unId);
        e.getListeIntervention().size();

        System.out.println("Hstorique des interventions effectuées par " + e.getPrenom() + " " + e.getNom() + " :");
        System.out.println(e.getListeIntervention());

        System.out.println("Total distance cumulé: " + e.getDistanceCumule() + "km");
    }

    public static void testerConsulterInterventionEnCours(Long unId) {
        System.out.println();
        System.out.println("**** testerConsulterInterventionEnCours() ****");
        System.out.println();

        Service service = new Service();
        Employe e = service.rechercherEmployeParId(unId);
        if (e.getInterventionEnCours() == null) {
            System.out.println(e.getPrenom() + " " + e.getNom() + " n'a pas d'intervention en cours.");
        } else {
            System.out.println("Intervention en cours de " + e.getPrenom() + " " + e.getNom() + " : ");
            System.out.println(e.getInterventionEnCours().toString());

        }

    }

}
