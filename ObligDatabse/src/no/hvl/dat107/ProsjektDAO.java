package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class ProsjektDAO {
	private static EntityManagerFactory emf;
	
	public ProsjektDAO() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	}
	public Prosjekt finnProsjekt(int idprosjekt) {
		
		
		EntityManager em = emf.createEntityManager();

        Prosjekt pr;
        try {
        	
            pr = em.find(Prosjekt.class, idprosjekt);
            
        } finally {
            em.close();
        }
		return pr;	
	}
	public int leggTilProsjekt(Prosjekt nyProsjekt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
			
				em.persist(nyProsjekt);
			
				tx.commit();
			
			} catch (Throwable e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				em.close();
			}
		
		return nyProsjekt.getProsjektID();
	}
	
	public List<Prosjekt> skrivUtAlle(){
		
			EntityManager em = emf.createEntityManager();
			List<Prosjekt> prosjekter = null;
			
			try {
				String queryString = "SELECT p FROM Prosjekt p ORDER BY p.idprosjekt";
				TypedQuery<Prosjekt> query = em.createQuery(queryString, Prosjekt.class);
				prosjekter = query.getResultList();
				
			} finally {
				em.close();
			}
			return prosjekter;
		
	}
}
