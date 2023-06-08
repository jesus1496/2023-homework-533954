package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO{
	
	Scanner scannerDiLinee;
	
	public IOConsole(Scanner scanner) {
		this.scannerDiLinee = scanner;
	}
	
	/**
	 * Stampa il messaggio su schermo per l'utente
	 * @param msg
	 */
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * Legge un'intera riga inserita come input da tastiera
	 * @return la riga scansionata
	 */
	public String leggiRiga() {
		
//		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		//scannerDiLinee.close()
		return riga;
	}

}
