package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.Agence;
import fr.insalyon.dasi.metier.modele.Intervention;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-11T18:49:46")
@StaticMetamodel(Employe.class)
public class Employe_ extends Personne_ {

    public static volatile SingularAttribute<Employe, Agence> agence;
    public static volatile SingularAttribute<Employe, Date> horaireFin;
    public static volatile SingularAttribute<Employe, Double> distanceCumule;
    public static volatile SingularAttribute<Employe, String> mdp;
    public static volatile ListAttribute<Employe, Intervention> listeIntervention;
    public static volatile SingularAttribute<Employe, Intervention> interventionEnCours;
    public static volatile SingularAttribute<Employe, Date> horaireDebut;
    public static volatile SingularAttribute<Employe, Boolean> dispo;

}