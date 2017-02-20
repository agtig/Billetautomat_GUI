package automat;

import java.util.ArrayList;
import java.util.Date;

/**
 * Model af en simpel billetautomat til enkeltbilletter med én fast pris.
 */
public class Billetautomat {
	private int billetpris;    // Prisen for én billet.
	private int balance; // Hvor mange penge kunden p.t. har puttet i automaten
	private int antalBilletterSolgt; // Antal billetter automaten i alt har solgt
	private boolean montørtilstand;
        private ArrayList<String> log = new ArrayList<String>();

	/**
	 * Opret en billetautomat der sælger billetter til 10 kr.
	 */
	public Billetautomat() {
		billetpris = 10;
		balance = 0;
		antalBilletterSolgt = 0;
                log.add(new Date() + ": Billetautomaten er startet. "
                        + "Billetprisen er sat til: " + billetpris + "kr. "
                        + "Balancen er: " + balance + "kr. "
                        + "Antal solgte billetter er: " + antalBilletterSolgt);
	}
        
        public void udskrivLog() {
            if (montørtilstand) {
                System.out.println("========== log pr " + new Date());
                for (String logIndhold : log) {
                    System.out.println(logIndhold);
                }
                System.out.println("==========");
            } else {
                System.out.println("Afvist - log ind først");
                log.add(new Date() + ": Uautoriseret forsøg på at udskrive log.");
            }
        }

	/**
	 * Giver prisen for en billet. 
	 */
	public int getBilletpris() {
		int resultat = billetpris;
		return resultat;
	}

	/**
	 * Modtag nogle penge (i kroner) fra en kunde.
	 */
	public void indsætPenge(int beløb) {
		balance = balance + beløb;
                log.add(new Date() + ": Der er indsat: " + beløb + "kr. "
                        + "Ny balance er: " + balance + "kr.");
	}

	/**
	 * Giver balancen (beløbet maskinen har modtaget til den næste billet).
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Udskriv en billet.
	 * Opdater total og nedskriv balancen med billetprisen
	 */
	public void udskrivBillet() {
		if (balance<billetpris) {   // tjek at der er penge til en billet
			System.out.println("Du mangler at indbetale nogle penge");
                        log.add(new Date() + ": Der blev forsøgt at købe en billet, men balancen var for lille.");
		}
                else {              // print kun en billet ud, hvis der er penge nok
		System.out.println("##########B##T#########");
		System.out.println("# BlueJ Trafikselskab #");
		System.out.println("#                     #");
		System.out.println("#        Billet       #");
		System.out.println("#        " + billetpris + " kr.       #");
		System.out.println("#                     #");
		System.out.println("##########B##T#########");
		System.out.println("# Du har " + (balance-billetpris) + " kr til gode       #");
		System.out.println("##########B##T#########");
		System.out.println();

		antalBilletterSolgt = antalBilletterSolgt + 1;
		balance = balance - billetpris; // Billetter koster 10 kroner
                log.add(new Date() + ": Der er udskrevet en billet.");
                }
	}


	public int returpenge() {
		int returbeløb = balance;
		balance = 0;
		System.out.println("Du får "+returbeløb+" kr retur");
                log.add(new Date() + ": Der er udbetalt " + returbeløb + "kr. "
                        + "Balancen er nu: " + balance + "kr.");
		return returbeløb;
	}

	
	void montørLogin(String adgangskode) {
		if ("1234".equals(adgangskode)) {
			montørtilstand = true;
			System.out.println("Montørtilstand aktiveret");
			System.out.println("Du kan nu angive billetpris");
                        log.add(new Date() + ": Montør logget ind.");
		} else {
			montørtilstand = false;
			System.out.println("Montørtilstand deaktiveret");
                        log.add(new Date() + ": Montør logget ud.");
		}
	}


	public int getTotal() {
		if (montørtilstand) {
			return billetpris * antalBilletterSolgt;
		} else {
			System.out.println("Afvist - log ind først");
			return 0;
		}
	}

	public int getAntalBilletterSolgt() {
		if (montørtilstand) {
			return antalBilletterSolgt;
		} else {
			System.out.println("Afvist - log ind først");
			return 0;
		}
	}

	public void setBilletpris(int billetpris) {
		this.billetpris = billetpris;
                log.add(new Date() + ": Ny billetpris angivet til: " + this.billetpris + "kr.");
	}

	public void nulstil() {
		if (montørtilstand) {
			antalBilletterSolgt = 0;
                        log.add(new Date() + ": Montør har nulstillet antal solgte billetter.");
		} else {
			System.out.println("Afvist - log ind først");
		}
	}

	public void setAntalBilletterSolgt(int antalBilletterSolgt) {
		if (montørtilstand) {
			this.antalBilletterSolgt = antalBilletterSolgt;
                        log.add(new Date() + ": Montør har sat antal solgte billetter til: " + antalBilletterSolgt);
		} else {
			System.out.println("Afvist - log ind først");
		}
	}

	public boolean erMontør() {
		return montørtilstand;
	}
}