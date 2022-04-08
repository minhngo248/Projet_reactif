package fr.insalyon.metier.modele;

import fr.insalyon.dasi.metier.modele.Agence;
import fr.insalyon.dasi.metier.modele.Employe;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-04T11:04:56")
@StaticMetamodel(Agence.class)
public class Agence_ { 

    public static volatile SingularAttribute<Agence, String> localisation;
    public static volatile SingularAttribute<Agence, Double> latitude;
    public static volatile SingularAttribute<Agence, Long> id;
    public static volatile SingularAttribute<Agence, String> nom;
    public static volatile ListAttribute<Agence, Employe> listeEmp;
    public static volatile SingularAttribute<Agence, Double> longitude;

}