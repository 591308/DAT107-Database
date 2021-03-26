package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ansatt",schema = "oblig_jpa")
@NamedQuery(name = "hentAlleAnsatte", query ="SELECT a FROM Ansatt a")
public class Ansatt {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idansatt;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansettelsesDato;
	private String stilling;
	private BigDecimal maanedslonn;

	@ManyToOne
	@JoinColumn(name = "idavdeling")
	private Avdeling avdeling;
	
	@ManyToMany(mappedBy="ansatte")
    private List<Prosjekt> prosjekter;
 
	 @OneToMany(mappedBy="ansatt")
	 private List<Prosjektdeltagelse> deltagelser;
	

	public Ansatt() {
		
	}
	
	public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ansettelsesDato, String stilling, BigDecimal maanedslonn, Avdeling avd) {
		
		this.brukernavn=brukernavn;
		this.fornavn=fornavn;
		this.etternavn=etternavn;
		this.ansettelsesDato=ansettelsesDato;
		this.stilling=stilling;
		this.maanedslonn=maanedslonn;
		this.avdeling=avd;
		
	}
	
	public void skrivUt(String innrykk) {
        System.out.printf("%sAnsatt nr %d: %s %s", innrykk, idansatt, fornavn, etternavn);
    }
	public void skrivUtMedProsjekter() {
        System.out.println();
        skrivUt("");
        prosjekter.forEach(p -> p.skrivUt("\n   "));
    }
	public void leggTilProsjekt(Prosjekt p) {
        prosjekter.add(p);
    }

    public void fjernProsjekt(Prosjekt p) {
        prosjekter.remove(p);
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
    
	public Integer getIdAnsatt() {
		return idansatt;
	}
	
	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public LocalDate getAnsettelsesDato() {
		return ansettelsesDato;
	}

	public void setAnsettelsesDato(LocalDate ansettelsesDato) {
		this.ansettelsesDato = ansettelsesDato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public BigDecimal getMaanedslonn() {
		return maanedslonn;
	}

	public void setMaanedslonn(BigDecimal maanedslonn) {
		this.maanedslonn = maanedslonn;
	}
	public Avdeling getDepartment() {
		return avdeling;
	}

	public void setDepartment(Avdeling avdeling) {
		this.avdeling = avdeling;
	}
	
	@Override
	public String toString() {
		KonsollTabell str = new KonsollTabell();
		str.setShowVerticalLines(true);
		str.setRightAlign(true);
		str.setHeaders("ID", "brukernavn", "fornavn", "etternavn", "AnsDate", "Stilling", " Lønn", "Avdeling", "SjefID");
		str.addRow(String.valueOf(idansatt), brukernavn, fornavn, etternavn, String.valueOf(ansettelsesDato), stilling, String.valueOf(maanedslonn), String.valueOf(getDepartment().getNavn()), String.valueOf(getDepartment().getIdansatt()));
		str.print();
		return "";
//		"Ansatt [ID: " + idansatt + "| Brukernavn: " + brukernavn + " | Navn: " + fornavn + " | Etternavn: "
//		+ etternavn + " | AnsDat: " + ansettelsesDato + " | Stilling: " + stilling + "| Ma.lønn: "
//		+ maanedslonn + "]" + "   [ Avdeling: " + getDepartment().getNavn() + " ]";
	}


}
