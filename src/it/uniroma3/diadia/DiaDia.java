package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comando.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanza tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final public String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	private Partita partita;
	private IO iO;

	public DiaDia(IO iO, Labirinto labirinto) {
		this.partita = new Partita(labirinto);	
		this.iO = iO;
	}

	public void gioca() throws Exception {
		String istruzione; 

		iO.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {	
			istruzione = this.iO.leggiRiga();
		}while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(this.iO);
		
		try {
			comandoDaEseguire = (Comando) factory.costruisciComando(istruzione);
		}catch(ClassNotFoundException cne) {
			comandoDaEseguire = (Comando) factory.costruisciComando("non valido");
		}catch(NullPointerException npe) {
			comandoDaEseguire = (Comando) factory.costruisciComando("non valido");
		}		
		
		comandoDaEseguire.esegui(this.partita);
		
//		if(comandoDaEseguire.getNome() == null) {
//			iO.mostraMessaggio("Devi digitare un comando");
//			return false;
//		}
//		if (comandoDaEseguire.getNome().equals("fine")) {
//			this.fine(); 
//			return true;
//		} 
//		else if (comandoDaEseguire.getNome().equals("vai"))
//			this.vai(comandoDaEseguire.getParametro());
//		
//		else if (comandoDaEseguire.getNome().equals("prendi"))
//			this.prendi(comandoDaEseguire.getParametro());
//		
//		else if (comandoDaEseguire.getNome().equals("posa"))
//			this.posa(comandoDaEseguire.getParametro());
//		
//		else if (comandoDaEseguire.getNome().equals("aiuto"))
//			this.aiuto();
//		else
//			iO.mostraMessaggio("comando sconosciuto");
		
		if (this.partita.vinta()) 
			iO.mostraMessaggio("Hai vinto!");
		if(!this.partita.giocatoreIsVivo())
			iO.mostraMessaggio("Hai esaurito CFU ... ");
		return this.partita.isFinita();
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
//	private void aiuto() {
//		for(int i=0; i< elencoComandi.length; i++) 
//			iO.mostraMessaggio(elencoComandi[i]+" ");
//		iO.mostraMessaggio("");
//	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
//	private void vai(Direzione direzione) {
//		if(direzione==null)
//			iO.mostraMessaggio("Dove vuoi andare ?");
//		Stanza prossimaStanza = null;
//		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
//		if (prossimaStanza == null)
//			iO.mostraMessaggio("Direzione inesistente");
//		else {
//			this.partita.setStanzaCorrente(prossimaStanza);
//			int cfu = this.partita.getCfu();
//			this.partita.setCfu(cfu--);
//		}
//		iO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
//	}
	
	/**
	 * Prende un attrezzo della stanza, lo copia nella borsa e poi lo rimuove dalla stanza
	 * @param attrezzo
	 * @return true se va tutto a buon fine altrimenti false con uno specifico messaggio di errore
	 */
//	public void prendi(String attrezzo) {
//		if(!this.partita.getStanzaCorrente().hasAttrezzo(attrezzo)) {
//			iO.mostraMessaggio("Attrezzo "+attrezzo+" non presente");
//			return;
//		}
//		Attrezzo nomeAttrezzo = this.partita.getStanzaCorrente().getAttrezzo(attrezzo);
//		this.partita.getGiocatore().getBorsa().addAttrezzo(nomeAttrezzo);
//		this.partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
//		iO.mostraMessaggio("Attrezzo "+ attrezzo +" preso!");
//		
//	}
//	
//	public void posa(String attrezzo) {
//		if(!this.partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo)) {
//			iO.mostraMessaggio("Attrezzo "+attrezzo+" non presente nella borsa");
//			return;
//		}
//		Attrezzo nomeAttrezzo = this.partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo); //attrezzo rimosso dalla borsa
//		this.partita.getStanzaCorrente().addAttrezzo(nomeAttrezzo);  //posa l'attrezzo nella stanza corrente
//		iO.mostraMessaggio("Attrezzo "+attrezzo+" posato!");
//	}
//	
//
//	/**
//	 * Comando "Fine".
//	 */
//	private void   fine() {
//		iO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
//	}

	public static void main(String[] argc) throws Exception{
		Scanner scanner = new Scanner(System.in);
		IO ioConsole = new IOConsole(scanner);
		Labirinto labirinto = Labirinto.newBuilder("labirinto5.txt").getLabirinto();
		DiaDia gioco = new DiaDia(ioConsole, labirinto);
		gioco.gioca();
		scanner.close();
	}
}