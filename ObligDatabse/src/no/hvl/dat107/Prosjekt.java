package no.hvl.dat107;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="prosjekt",schema="oblig_jpa")
@NamedQuery(name = "hentalleProsjekter", query = "SELECT p FROM Prosjekt p")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idprosjekt;
	private String prosjektnavn;
	private String beskrivelse;
	
	 @OneToMany(mappedBy="prosjekt")
	    private List<Prosjektdeltagelse> deltagelser;
	
	 @ManyToMany
	    @JoinTable(
	            name = "oblig_jpa.prosjektdeltagelse", // NB! Må ha med schema !!!
	            joinColumns = @JoinColumn(name="idprosjekt"),
	            inverseJoinColumns = @JoinColumn(name="idansatt"))
	    private List<Ansatt> ansatte;
	
	 
	public Prosjekt() {
		
	}
	
	public Prosjekt(String prosjektnavn, String beskrivelse) {
		this.prosjektnavn=prosjektnavn;
		this.beskrivelse=beskrivelse;
	}
	
	
	public Integer getProsjektID() {
		return idprosjekt;
	}
	
	public String getNavn() {
		return prosjektnavn;
	}

	public void setNavn(String navn) {
		this.prosjektnavn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	
	public void skrivUt(String innrykk) {
        System.out.printf("%sProsjekt nr %d: %s", innrykk, idprosjekt, prosjektnavn);
    }
    public void skrivUtMedAnsatte() {
        System.out.println();
        skrivUt("");
        ansatte.forEach(a -> a.skrivUt("\n   "));
    }

    public void leggTilAnsatt(Ansatt a) {
        ansatte.add(a);
    }

    public void fjernAnsatt(Ansatt a) {
        ansatte.remove(a);
    }
   
    public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.add(prosjektdeltagelse);
    }

    public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.remove(prosjektdeltagelse);
    }
    
    public List<Prosjektdeltagelse> getDeltagelser() {
		return deltagelser;
	}
	@Override
	public String toString() {
		KonsollTabell str = new KonsollTabell();
		str.setShowVerticalLines(true);
		str.setRightAlign(true);
		str.setHeaders("Prosjekt ID", "Prosjektnavn", "Prosjekt Beskrivelse");
		str.addRow(String.valueOf(idprosjekt), String.valueOf(prosjektnavn), String.valueOf(beskrivelse));
		str.print();
		return "";
	}
}
