package no.hvl.dat107;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Avdeling", schema = "oblig_jpa")
@NamedQuery(name = "hentalleAvdelinger", query ="SELECT a FROM Avdeling a")
public class Avdeling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idavdeling;
	private String navn;
	
	private Integer idansatt;
	
	@OneToMany(mappedBy = "avdeling")
	private List<Ansatt> ansatt;

	
	public Avdeling() {
		
	}
	public Avdeling(String navn, Integer idansatt) {
		this.navn=navn;
		this.idansatt=idansatt;
	}
	
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	@Override
	public String toString() {
		KonsollTabell st = new KonsollTabell();
		st.setShowVerticalLines(true);
		st.setHeaders("Avdeling ID", "Avddelingsnavn", "Sjef ID");
		st.addRow(String.valueOf(idavdeling), navn, String.valueOf(idansatt));
		st.print();
		return "";
	}

	public Integer getIdansatt() {
		return idansatt;
	}

	public void setIdansatt(Integer ansatt) {
		this.idansatt = ansatt;
	}
	public Integer getIdAvdeling() {
		return idavdeling;
	}
	public List<Ansatt> getEmployees() {
		return ansatt;
	}

	public void setEmployees(List<Ansatt> employees) {
		this.ansatt = employees;
	}
		
}
