package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AnsattDAO {
	
	private static EntityManagerFactory emf;
	
	public AnsattDAO() {
		
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	}
	
	//finn ansatt på ansatt id
	public Ansatt finnAnsattMedAnsattID(int idansatt) {
		
		        EntityManager em = emf.createEntityManager();

		        Ansatt ansatt;
		        try {
		            ansatt = em.find(Ansatt.class, idansatt);
		            
		        } finally {
		            em.close();
		        }
				return ansatt;
		       
	}
	//finn ansatt med brukernavn
	public Ansatt finnAnsattMedBrukerNavn(String brukernavn) {
		
		
		EntityManager em = emf.createEntityManager();
        
        try {
        	
        	String queryString = "SELECT a FROM Ansatt a Where a.brukernavn = :brukernavn";
            TypedQuery<Ansatt> querry = em.createQuery(queryString, Ansatt.class);
           
            querry.setParameter("brukernavn", brukernavn);
            
            return querry.getSingleResult();
            
        } finally {
            em.close();
        }
    }
	//utlisting av alle ansatte
	public List<Ansatt> skrivUtAlle(){
		
		EntityManager em = emf.createEntityManager();
		List<Ansatt> personer = null;
		
		try {
			String queryString = "SELECT a FROM Ansatt a ORDER BY a.idansatt";
			TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
			personer = query.getResultList();
			
		} finally {
			em.close();
		}
		return personer;
	}
	
	//Oppdatere 
	public void oppdaterAnsatt(Ansatt p) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			em.merge(p);
			
			tx.commit();
			
		} catch (Throwable e) {
			e.printStackTrace();
			if(tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	//Legg inn en ny ansatt
	public int settInnNyAnsatt(Ansatt nyAnsatt) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
			
				em.persist(nyAnsatt);
			
				tx.commit();
			
			} catch (Throwable e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				em.close();
			}
		
		return nyAnsatt.getIdAnsatt();
	}
	public void slettAnsatt(int idansatt) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			em.remove(em.find(Ansatt.class, idansatt));
			
			tx.commit();
			
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
	}
}
