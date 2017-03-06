package automat;

import java.util.ArrayList;
import java.util.Date;

/**
 * Model af en simpel billetautomat til enkeltbilletter med én fast pris.
 */
public class Billetautomat {
	private int billetpris;                             // Prisen for én billet.
	private int balance;                                // Hvor mange penge kunden p.t. har puttet i automaten
	private int antalBilletterSolgt;                    // Antal billetter automaten i alt har solgt
	private boolean montørtilstand;                     // Declarer
        private ArrayList<Log> log = new ArrayList<Log>();  // Declarer log-array

	/**
	 * Opret en billetautomat der sælger billetter til 10 kr.
	 */
	public Billetautomat() {
		billetpris = 10;                                // Sæt billetprisen
		balance = 0;                                    // Nulstil balancen
		antalBilletterSolgt = 0;                        // Nulstil antal solgte billetter
                Log nyLog = new Log();                          // Start en log
                nyLog.tid = new Date();                         // Log tid og dato for start af automaten
                nyLog.handling =  "Billetautomaten er startet.";// Skriv til log
                nyLog.billetPrisNu = billetpris;                // Log billetpris
                nyLog.balanceNu = balance;                      // Log balancen
                nyLog.solgteBilletterNu = antalBilletterSolgt;  // Log antal solgte billetter
                log.add(nyLog);                                 // Skriv til log
	}
        
        /**
         * Udskriv log til skærm
         */
        public void udskrivLog() {
            if (montørtilstand) {
                System.out.println("========== log pr " + new Date());
                for (Log logIndhold : log) {
                    System.out.println(logIndhold.tid + ": " + logIndhold.handling);
                    System.out.println("\tBilletpris: " + logIndhold.billetPrisNu + 
                            "\tBalance: " + logIndhold.balanceNu +
                            "\tAntal solgte billetter: " + logIndhold.solgteBilletterNu);
                }
                System.out.println("==========");
            } else {
                System.out.println("Afvist - log ind først");
                //log.add(new Date() + ": Uautoriseret forsøg på at udskrive log.");
            }
        }

	/**
	 * Returnerer prisen for en billet. 
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
                Log nyLog = new Log();
                nyLog.tid = new Date();
                nyLog.handling = ("Der er indsat penge: " + beløb + " kr.");
                nyLog.billetPrisNu = billetpris;
                nyLog.balanceNu = balance;
                nyLog.solgteBilletterNu = antalBilletterSolgt;
                nyLog.indsatBeløb = beløb;
                log.add(nyLog);
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
                //        log.add(new Date() + ": Der blev forsøgt at købe en billet, men balancen var for lille.");
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
                Log nyLog = new Log();
                nyLog.tid = new Date();
                nyLog.handling = ("Der er udstedt en billet.");
                nyLog.billetPrisNu = billetpris;
                nyLog.balanceNu = balance;
                nyLog.solgteBilletterNu = antalBilletterSolgt;
                log.add(nyLog);
                }
	}

	public int returpenge() {
		int returbeløb = balance;
		balance = 0;
		System.out.println("Du får "+returbeløb+" kr retur");
                //log.add(new Date() + ": Der er udbetalt " + returbeløb + "kr. "
                Log nyLog = new Log();
                nyLog.tid = new Date();
                nyLog.handling = ("Der er udbetalt " + returbeløb + " kr.");
                nyLog.billetPrisNu = billetpris;
                nyLog.balanceNu = balance;
                nyLog.solgteBilletterNu = antalBilletterSolgt;
                nyLog.udbetaltBeløb = returbeløb;
                log.add(nyLog);
		return returbeløb;
	}
	
	void montørLogin(String adgangskode) {
		if ("1234".equals(adgangskode)) {
			montørtilstand = true;
			System.out.println("Montørtilstand aktiveret");
			System.out.println("Du kan nu angive billetpris");
                //        log.add(new Date() + ": Montør logget ind.");
                Log nyLog = new Log();
                nyLog.tid = new Date();
                nyLog.handling = ("Montør er logget ind.");
                nyLog.billetPrisNu = billetpris;
                nyLog.balanceNu = balance;
                nyLog.solgteBilletterNu = antalBilletterSolgt;
                log.add(nyLog);
		} else {
			montørtilstand = false;
			System.out.println("Montørtilstand deaktiveret");
                //        log.add(new Date() + ": Montør logget ud.");
                Log nyLog = new Log();
                nyLog.tid = new Date();
                nyLog.handling = ("Montør er logget ud.");
                nyLog.billetPrisNu = billetpris;
                nyLog.balanceNu = balance;
                nyLog.solgteBilletterNu = antalBilletterSolgt;
                log.add(nyLog);
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
                Log nyLog = new Log();
                nyLog.tid = new Date();
                nyLog.handling = ("Ny billetpris er angivet til: " + this.billetpris + " kr.");
                nyLog.billetPrisNu = this.billetpris;
                nyLog.balanceNu = balance;
                nyLog.solgteBilletterNu = antalBilletterSolgt;
                log.add(nyLog);
	}

	public void nulstil() {
		if (montørtilstand) {
			antalBilletterSolgt = 0;
                Log nyLog = new Log();
                nyLog.tid = new Date();
                nyLog.handling = ("Montør har nulstillet antal solgte billetter.");
                nyLog.billetPrisNu = billetpris;
                nyLog.balanceNu = balance;
                nyLog.solgteBilletterNu = antalBilletterSolgt;
                log.add(nyLog);
		} else {
			System.out.println("Afvist - log ind først");
		}
	}

	public void setAntalBilletterSolgt(int antalBilletterSolgt) {
		if (montørtilstand) {
			this.antalBilletterSolgt = antalBilletterSolgt;
                Log nyLog = new Log();
                nyLog.tid = new Date();
                nyLog.handling = ("Montør har sat antal solgte billetter til: " + antalBilletterSolgt);
                nyLog.billetPrisNu = billetpris;
                nyLog.balanceNu = balance;
                nyLog.solgteBilletterNu = antalBilletterSolgt;
                log.add(nyLog);
		} else {
			System.out.println("Afvist - log ind først");
		}
	}

	public boolean erMontør() {
		return montørtilstand;
	}
}