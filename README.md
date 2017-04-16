#TP 2 SIR R�alis� par Meriem Machnache & Lydia Moussa

Le but de ce tp est de créer une application type réseau social pour comparer 
la consommation électrique avec ses amis, ses voisins ... dans une société américaine opower.

## Objectifs du TP

-Comprendre les mécanismes de JPA
-Réaliser une application en utilisant JPA en se plaçant dans un cadre classique de développement sans serveur d’application au départ.

# Présentation des règles de gestion des données

Person: id,nom,prénom,mail, une ou plusieurs résidences , plusieurs devices , un ou plusieurs amis.
Home: id,taille,nombre de pieces
Equipement electroniques: consommation moyenne en Watt/h.
Heater: nom chauffage,puissance

# Utilisation JPA, hibernate et hsqldb. 

Nous avons utilisé deux scripts.

Un script run-hsqldb-server.sh pour démarrer la base de données.
Un script show-hsqldb.sh pour démarrer le Manager.
 
### Le fichier de configuration pom.xml contient les dépendances nécessaires
hibernate , hsqldb et mysql(driver jdbc pour hsqldb).
 	<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>4.1.7.Final</version>
	 </dependency>
	 <dependency>
			<groupId>org.hsqldb</groupId>
			<artifactId>hsqldb</artifactId>
			<version>2.2.8</version>
	 </dependency>
	 <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.21</version>
	 </dependency>
	 
	 
![model](https://cloud.githubusercontent.com/assets/15005875/24684497/b92f1ffe-19a6-11e7-9628-f9a097ea34e2.png)
 
### Nous avons modifié le fichier de configuration persistence.xml pour paramétrer la base de données.
  
 	 <properties>
         <property name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect"/>
	     <property name="hibernate.hbm2ddl.auto" value="create"/>
    	 <property name="toplink.target-database" value="HSQL"/>
         <property name="hibernate.connection.driver_class" value="org.hsqldb.jdbcDriver"/>
         <property name="hibernate.connection.username" value="sa"/>
         <property name="hibernate.connection.password" value=""/>
         <property name="hibernate.connection.url" value="jdbc:hsqldb:hsql://localhost/"/>
         <property name="hibernate.max_fetch_depth" value="3"/>
      </properties>
      
      
 
# Représentation des classes 

### La classe Personne
### Représentation des attributs et des annotations


La classe Personne est composée des attributs suivants : id généré automatiquemnt, nom, prenom,mail.
La classe Personne a trois collection friends, residences, devices.

Une personne peut avoir un ou plusieurs amis.
Une personne peut avoir une ou plusieurs résidences.
Une personne peut avoir un ou plusieurs équipements électroniques.
  
Nous avons ajouté les annotations suivantes:

@Entity : pour définir une entité

@Id @GeneratedValue: Pour générer l'id automatiquement

@OneToMany (mappedBy="personnes", cascade=CascadeType.PERSIST): Pour exprimer la relation une ou 		plusieurs entre l'entité personne et les autres entités residence, devices.

 
# La classe Home

La classe Home est composée des attributs suivants:id généré automatiquemnt,nombre de pièces,taille , collection de heaters.
Une résidence peut avoir une ou plusieurs heaters. 
   
Nous avons ajouté les annotations suivantes:

@Entity : pour définir entité Home
@Id @GeneratedValue: Pour générer l'id automatiquement
@OneToMany(mappedBy="homes", cascade=CascadeType.PERSIST): Pour exprimer la relation entre home et heaters , une résidence peut avoir une ou plusieurs heaters.
@ManyToOne: Une maison peut avoir une seule personne.



# La classe ElectronicDevice 

La classe ElectronicDevice est composée des attribus suivants:
Id et consommation , qui représente la consommation moyenne en Watt/h
et un attribut de type personne.

Nous avons ajouté les annotations suivantes:
@Entity : pour définir l'entité ElectronicDevice
@Id @GeneratedValue: Pour générer l'id automatiquement
@ManyToOne: Une personne peut avoir une ou plusieurs équipements électroniques 



# La classe Heater

La classe Heater.java est composée des attributs suivants:id, nom, puissance
et un attribut de type home.

Nous avons ajouté les annotations suivantes:
@Entity : pour définir l'entitée Heater
@Id @GeneratedValue: Pour générer l'id automatiquement
@ManyToOneUne: Une résidence peut avoir une ou plusieurs heaters. 


# La classe Device
Cette classe représente la classe mère pour les deux classes :
Heater et ElectronicDevice. public class ElectronicDevice extends Device{}
 
# La classe de service

La classe JpaTest est composée d'un attribut de type EntityManager.

EntityManager nous permet de faire les opérations de persistance.
Nous avons crée une instance de la classe EntityManager . 

Pour le faire, nous avons passé par la fabrique Factory qu'on récupère avec
la méthode statique Persistence.createEntityManagerFactory() 
 
Dans cette classe de service, nous avons immplementé les transactions afin de mettre à  jour les données, en executant les opérations CRUD: Insert,Update,Delete,..

 public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("example");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		try {
			test.createHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tx.commit();
		test.listHome();
		manager.close();
		factory.close();
		
		System.out.println("....Done");
}
  
 
Le code pour créer des personnes:
 
		Person person1 = new Person("machnache", "meriem", "machnache@gmail.fr");
		manager.persist(person1);
		Person person2 = new Person("moussa", "lydia", "moussa@yahoo.fr");
		manager.persist(person2);
		
		
L'utilisation des criteria query:

		List<Home> resultList = manager.createQuery("SELECT a FROM Home a", 		Home.class).getResultList();
		System.out.println("le nombre de maisons est: " + resultList.size());