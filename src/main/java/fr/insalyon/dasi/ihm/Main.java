/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Agence;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Livraison;
import fr.insalyon.dasi.metier.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author bbbbb
 */
public class Main {

    public static void main(String[] args) throws ParseException {
        JpaUtil.init();
        //testInscriptionClient();
        testInitialiserEmp();
        //testCreerAgence();
        //testRechercheClient();
        //testDemanderIntervention();
        JpaUtil.destroy();
    }

    public static void testInscriptionClient() throws ParseException {
        Service service = new Service();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dt = "01/06/2001";
        Client minh = new Client("Minh", "NGO", "minh.ngo@insa-lyon.fr", "123AZE", "10 avenue Albert Einstein 69100 Villeurbanne", "123456789", sdf.parse(dt));
        Long idMinh = service.inscrireClient(minh);
        if (idMinh != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
    }

    public static void testCreerAgence() {
        JpaUtil.creerContextePersistance();
        try {
            Agence a = new Agence("React'IF Villeurbanne", "1 Avenue Roger Salengro, 69 100 Villeurbanne");
            JpaUtil.ouvrirTransaction();
            JpaUtil.obtenirContextePersistance().persist(a);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public static void testInitialiserEmp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
        String date;
        JpaUtil.creerContextePersistance();
        try {
            Agence a = JpaUtil.obtenirContextePersistance().find(Agence.class, Long.valueOf(51));
            JpaUtil.ouvrirTransaction();
            date = "08/08/1995";
            Employe e1 = new Employe("111", sdf1.parse("00:00:00"), sdf1.parse("08:00:00"), a,
                    "PAIR", "Benoît", "benoit.pair@reactif.fr", "06 79 89 06 99", sdf.parse(date));
            Employe e2 = new Employe("222", sdf1.parse("01:00:00"), sdf1.parse("07:00:00"), a,
                    "FOLTÊTE", "Emile", "emile.foltete@reactif.fr", "06 79 74 09 99", sdf.parse(date));
            JpaUtil.obtenirContextePersistance().persist(e1);
            JpaUtil.obtenirContextePersistance().persist(e2);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public static void testRechercheClient() {
        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();     
        
        Service service = new Service();
        Client client = service.rechercherClientParId(Long.valueOf(2));
        if (client != null) {
            System.out.println("> Succès recherche client");
        } else {
            System.out.println("> Échec recherche client");
        }
        System.out.println();
    }

    public static void testDemanderIntervention() throws ParseException {
        Service service = new Service();
        Client minh = service.rechercherClientParId(Long.valueOf(1));
        
        Instant instant = Instant.now();
        Date oldfashionedDateObject = Date.from(instant);
        Livraison livraison = new Livraison("Bracelet", "COAI", "Livraison, svp", oldfashionedDateObject, minh);
        Employe e = service.demanderIntervention(minh, livraison);
        if (e == null) {
            System.out.println("Veuillez demander ultérieurement.");
        }
    }
}
