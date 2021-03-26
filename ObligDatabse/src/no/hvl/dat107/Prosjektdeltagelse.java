package no.hvl.dat107;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="prosjektdeltagelse",schema ="oblig_jpa")
public class Prosjektdeltagelse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prosjektdeltagelse_Id;
	
	@ManyToOne
	@JoinColumn(name="idansatt")
	Ansatt ansatt;
	
	@ManyToOne
	@JoinColumn(name="idprosjekt")
	Prosjekt prosjekt;
	
	private String rolle;
	private Integer timer;
	
	public Prosjektdeltagelse() {
	}
   
	public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, String rolle) {
		this.ansatt=ansatt;
		this.prosjekt=prosjekt;
		this.rolle=rolle;
		ansatt.leggTilProsjektdeltagelse(this);
        prosjekt.leggTilProsjektdeltagelse(this);
	}
	public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, String rolle, int timer) {
		this.ansatt=ansatt;
		this.prosjekt=prosjekt;
		this.timer=timer;
		
		ansatt.leggTilProsjektdeltagelse(this);
        prosjekt.leggTilProsjektdeltagelse(this);
	}
	
	public int getProsjektDeltagelseID() {
		return prosjektdeltagelse_Id;
	}
	
	public String getRolle() {
		return rolle;
	}
	public void setRolle(String rolle) {
		this.rolle = rolle;
	}
	public Integer getTimer() {
		return timer;
	}
	public void setTimer(Integer timer) {
		this.timer = timer;
	}
	
	 public void skrivUt(String innrykk) {
	        System.out.printf("Deltagelse: ProDelt.Id:%d, Navn:%s, Etternavn%s, ProNavn%s ,Prosjekt Id:%s, Timer: %d, Rolle:%s", prosjektdeltagelse_Id, 
	                ansatt.getFornavn(), ansatt.getEtternavn(), prosjekt.getNavn(), prosjekt.getProsjektID(), timer, rolle + "\n");
	    }
}


