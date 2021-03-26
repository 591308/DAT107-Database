package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AvdelingDAO {

	private static EntityManagerFactory emf;
	
	public AvdelingDAO() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	}
	
	public Avdeling finnAvdelingMedId(int id) {
		
		EntityManager em = emf.createEntityManager();

        Avdeling avdeling=null;
        try {
             avdeling = em.find(Avdeling.class, id);
           
        } finally {
        	em.close();
        }
		return avdeling;
	}
	
	public int leggTilAvdeling(Avdeling nyAvdeling) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
		
			em.persist(nyAvdeling);
		
			tx.commit();
		
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	
	return nyAvdeling.getIdAvdeling();
	
	}
	
	public List<Avdeling> skrivUtAlle(){
		
		EntityManager em = emf.createEntityManager();
		List<Avdeling> avdelinger = null;
		
		try {
			String queryString = "SELECT a FROM Avdeling a ORDER BY a.idavdeling";
			TypedQuery<Avdeling> query = em.createQuery(queryString, Avdeling.class);
			avdelinger = query.getResultList();
			
		} finally {
			em.close();
		}
		return avdelinger;
	}
}
