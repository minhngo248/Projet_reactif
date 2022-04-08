package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.Intervention;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-08T15:44:08")
@StaticMetamodel(Client.class)
public class Client_ extends Personne_ {

    public static volatile SingularAttribute<Client, String> addPostal;
    public static volatile SingularAttribute<Client, Double> latitude;
    public static volatile SingularAttribute<Client, String> mdp;
    public static volatile ListAttribute<Client, Intervention> historique;
    public static volatile SingularAttribute<Client, Double> longitude;

}