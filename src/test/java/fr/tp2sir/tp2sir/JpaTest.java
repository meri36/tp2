package fr.tp2sir.tp2sir;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;




public class JpaTest {

	private static EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	

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
	
		private void createHome() {
			
			Person person1 = new Person("machnache", "meriem", "machnache@gmail.fr");
			manager.persist(person1);
			Person person2 = new Person("moussa", "lydia", "moussa@yahoo.fr");
			manager.persist(person2);
			Person person3 = new Person("machnache", "karima", "karima@gmail.fr");
			manager.persist(person3);
			
			Home home1 = new Home(25,50,person1);
			manager.persist(home1);
			
			Home home2 = new Home(50,100,person1);
			manager.persist(home2);
			
			ElectronicDevice ElectronicDevice1 = new ElectronicDevice();
			ElectronicDevice1.setName("ElectronicDevice1");
			ElectronicDevice1.setCconsommation(4);
			ElectronicDevice1.setPersonnes(person1);
			manager.persist(ElectronicDevice1);
			
		 	ElectronicDevice ElectronicDevice2 = new ElectronicDevice();
		 	ElectronicDevice2.setName("ElectronicDevice2");
		 	ElectronicDevice2.setCconsommation(10);
		 	ElectronicDevice2.setPersonnes(person1);
			manager.persist(ElectronicDevice2);
			
			ElectronicDevice ElectronicDevice3 = new ElectronicDevice();
			ElectronicDevice3.setName("ElectronicDevice3");
			ElectronicDevice3.setCconsommation(50);
			ElectronicDevice3.setPersonnes(person2);
			manager.persist(ElectronicDevice3); 
			
			Heater heater1 = new Heater();
			heater1.setNom_chauffage("heater1");
			heater1.setPuissance(40);
			heater1.setHomes(home1);
			manager.persist(heater1);
		
			Heater heater2 = new Heater();
			heater2.setNom_chauffage("Heater2");
			heater2.setPuissance(60);
			heater2.setHomes(home1);
			manager.persist(heater2);
			}
	  
		
		private void listHome() {
		List<Home> resultList = manager.createQuery("SELECT a FROM Home a", Home.class).getResultList();
		System.out.println("le nombre de maisons est: " + resultList.size());
		
		for (Home next : resultList) {
			System.out.println("Maison de : " + next.getperson_home().getPrenom());
		}
	}
	
}
