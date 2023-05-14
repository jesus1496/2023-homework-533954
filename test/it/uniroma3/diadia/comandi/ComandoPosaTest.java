package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comando.ComandoPosa;

public class ComandoPosaTest {

	private Attrezzo attrezzo;
	private ComandoPosa comandoPosa;
	private Partita partita;
	private IOConsole io;
	
	@Before
	public void setUp() throws Exception {
		this.comandoPosa = new ComandoPosa();
		this.comandoPosa.setIo(new IOConsole());
		this.partita = new Partita();
		this.attrezzo = new Attrezzo("martello", 2); 
	}
	
	@Test
	public void testAttrezzoPosato() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comandoPosa.setParametro("martello");
		comandoPosa.esegue(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	@Test 
	public void testAttrezzoPosatoNull(){
		comandoPosa.setParametro("martello");
		comandoPosa.esegue(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	public void creatoreAttrezzi() {
		for(int i = 0; i<10; i++) {
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("utensille"+i, 1));
		}
	}
	
	@Test
	public void testTroppiAttrezzi() {
		this.creatoreAttrezzi();
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comandoPosa.setParametro("martello");
		comandoPosa.esegue(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
//	@Test
//	public void testEseguiStanzaPiena() {
//		Stanza stanzaCorrente = partita.getStanzaCorrente(); //andiamo a rimpire la stanza attrezzi
//		for(int i=0; i<Stanza.NUMERO_MASSIMO_ATTREZZI; i++) {
//			stanzaCorrente.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
//		}
//		//qua proviamo a posare un'attrezzo
//		this.comandoPosa.setParametro(ATTREZZO_DA_POSARE);;
//		this.comandoPosa.esegue(partita);
//		assertFalse(stanzaCorrente.hasAttrezzo(ATTREZZO_DA_POSARE));
//		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(ATTREZZO_DA_POSARE));
//	}
}
