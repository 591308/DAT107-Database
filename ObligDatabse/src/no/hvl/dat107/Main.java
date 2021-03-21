package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static AnsattDAO dao;
	private static AvdelingDAO daoo;
	
	public Main() {
		dao = new AnsattDAO();
		daoo = new AvdelingDAO();
	}

	public static void main(String[] args) {
		Main gr = new Main();
		gr.start();
	}
	
	public static void start() {
		
		System.out.println("Søk etter ansatt på ansatt id:    1");
		System.out.println("Søk etter ansatt på brukernavn:   2");
		System.out.println("Utlisting av alle ansatte:        3");
		System.out.println("Oppdatere en ansatt:              4");
		System.out.println("Legg til en ny ansatt:            5");
		System.out.println("Slett en ansatt:                  6");
		System.out.println("Søk etter avdeling:               7");
		System.out.println("Skriv ut ansatte i avdelingen:    8");
		System.out.println("Legg til ny avdeling:             9");
		System.out.println("Avslutt program:                 10\n");
		
		Scanner in = new Scanner(System.in);
		
		int valg = in.nextInt();
		
		switch(valg) {
		case 1://Søk etter ansatt ansatt id
			
			System.out.println("Skriv in id til ansatt du vil finne: ");
			Ansatt ansattIDfinn = dao.finnAnsattMedAnsattID(in.nextInt());

			System.out.println(ansattIDfinn);
			
			start();
		
			break;
		case 2://Søk etter ansatt brukernavn
			System.out.println("Skriv in brukernavn til ansatt du vil finne: ");
			
			Ansatt ansattBrukFinn = dao.finnAnsattMedBrukerNavn(in.next());
				
			System.out.println(ansattBrukFinn);
			
			System.out.println("");
			start();
			break;
			
		case 3://Utlis alle ansatte
			
			AnsattDAO p = new AnsattDAO();
			for(Ansatt a : p.skrivUtAlle()) {
				System.out.println(a);
			}
			System.out.println("");
			start();
			break;
			
		case 4://Oppdatere ansatt
			
			System.out.println("Finn du vil endre med å bruke ansatt ID: ");
			int z = in.nextInt();
			Ansatt ansatt = dao.finnAnsattMedAnsattID(z);
			System.out.println(ansatt);
			
			System.out.println("Skriv inn hva du vil endre (brukernavn, fornavn, etternavn, ansattelsesdato, stilling, manedslonn, avdeling");
			String inn = in.next();
			if(inn.equals("brukernavn)")) {
				ansatt.setBrukernavn(in.next());
				dao.oppdaterAnsatt(ansatt);
			} else if(inn.equals("fornavn")) {
				System.out.println("Tast inn ønsket fornavn: ");
				ansatt.setFornavn(in.next());
				dao.oppdaterAnsatt(ansatt);
				
			} else if(inn.equals("etternavn")) {
				System.out.println("Tast inn ønsket etternavn: ");
				ansatt.setEtternavn(in.next());
				dao.oppdaterAnsatt(ansatt);
				
			}else if(inn.equals("ansattelsesdato")) {
				System.out.println("Skriv inn dato (År XXXX, måned, XX, dag XX");
				LocalDate date = LocalDate.of(in.nextInt(), in.nextInt(), in.nextInt());
				ansatt.setAnsettelsesDato(date);
				dao.oppdaterAnsatt(ansatt);
				
			}else if(inn.equals("stilling")) {
				System.out.println("Tast inn ønsket stilling: ");
				ansatt.setStilling(in.next());
				dao.oppdaterAnsatt(ansatt);
				
			}else if(inn.equals("manedslonn")) {
				
				System.out.println("Tast inn ønsket lønn: ");
				ansatt.setMaanedslonn(in.nextBigDecimal());
				dao.oppdaterAnsatt(ansatt);
			}else if(inn.equals("avdeling")) {
				
				System.out.println("Tast inn ønsket avdeling: ");
				
				Avdeling avd = daoo.finnAvdelingMedId(in.nextInt());
				if(!ansatt.getDepartment().getIdansatt().equals(ansatt.getIdAnsatt())) {
					ansatt.setDepartment(avd);
					dao.oppdaterAnsatt(ansatt);
				} else {
				System.out.println("Kan ikkje endre avdeling sida ansatt er sjef i en annen avdeling\n");
				}
			}
			
			start();
			break;
		
		case 5://Legg til ny ansatt
			
			System.out.println("Skriv in brukernavn 4 bokstaver: ");
			String brukernavn = in.next();
			System.out.println("Skriv inn fornavn: ");
			String fornavn = in.next();
			System.out.println("Skriv in etternavn: ");
			String etternavn = in.next();
			System.out.println("Skriv in ansattelsesdato (År XXXX, måned XX, dag XX): ");
			LocalDate date = LocalDate.of(in.nextInt(), in.nextInt(), in.nextInt());
			System.out.println("Skriv in stilling: ");
			String stilling = in.next();
			System.out.println("Skriv in lonn: ");
			BigDecimal maanedslonn = in.nextBigDecimal();
			System.out.println("Skriv in avdelingId: ");
			Avdeling avd = daoo.finnAvdelingMedId(in.nextInt());
			
			Ansatt ansattNy = new Ansatt(brukernavn, fornavn, etternavn, date, stilling, maanedslonn, avd);
			dao.settInnNyAnsatt(ansattNy);
			
						
			start();
			break;
		case 6://Slett ansatt
			
			System.out.println("Skriv in ansattID for å slette ansatt: ");
			int s = in.nextInt();
			Ansatt ans = dao.finnAnsattMedAnsattID(s);
			
			if(!ans.getDepartment().getIdansatt().equals(ans.getIdAnsatt())) {
				dao.slettAnsatt(s);
			} else {
			System.out.println("Kan ikkje slette sida ansatt er sjef i en avdeling\n");
			}
			
			
			start();
			break;
		case 7://Finn avdeling med id
			System.out.println("Finn avdeling ved å taste avdelings id");
			
			Avdeling avdeling = daoo.finnAvdelingMedId(in.nextInt());
			System.out.println("Avdelings Sjef er : " + dao.finnAnsattMedAnsattID(avdeling.getIdansatt()).getFornavn());
			System.out.println(avdeling);
		
			start();
			break;
		case 8://Skriv ut alle personer på spesifikk avdeling
			
			System.out.println("Skriv inn avdeling id du ønsker å skrive ut alle ansatte for");
			
			Avdeling avdeling1 = daoo.finnAvdelingMedId(in.nextInt());
			System.out.println("Avdelings Sjef er : " + dao.finnAnsattMedAnsattID(avdeling1.getIdansatt()).getFornavn());
			for(Ansatt a : avdeling1.getEmployees()) {
				System.out.println(a);
			}
			
			start();
			
			break;
		case 9:
			
			System.out.println("Skriv in navn på ny avdeling: ");
			String navn = in.next();
			System.out.println("Finn ansatt du vil velge som sjef: ");
			int an = in.nextInt();
			Ansatt ansattID = dao.finnAnsattMedAnsattID(an);
			int i = 0;
			Avdeling avde = null;
			
			if(!ansattID.getDepartment().getIdansatt().equals(ansattID.getIdAnsatt())) {
				
				avde = new Avdeling(navn, ansattID.getIdAnsatt());
				
				i = daoo.leggTilAvdeling(avde);
				
			} else {
				System.out.println("Ansatt er allerede sjef i en annen avdeling\n");
			}
			ansattID.setDepartment(avde);
			
			dao.oppdaterAnsatt(ansattID);
			
			start();
			break;
		default:
			
			in.close();
		}
		
	}
	
}
