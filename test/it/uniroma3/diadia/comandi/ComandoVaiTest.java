package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comando.ComandoVai;

public class ComandoVaiTest {

	private  static final String NOME_STANZA_PARTENZA = "Partenza";
	private static final String NORD = "Nord";
	private Partita partita;
	private ComandoVai comandoVai;
	private Stanza partenza;
	
	@Before
	public void setUp() throws Exception{
		this.comandoVai = new ComandoVai();
		this.comandoVai.setIo(new IOConsole());
		this.partita = new Partita();
		this.partenza = new Stanza(NOME_STANZA_PARTENZA);
		this.partita.setStanzaCorrente(partenza);
	}
	
	@Test
	public void testVaiStanzaNonPresente() {
		this.comandoVai.setParametro(NORD);
		this.comandoVai.esegue(partita);
		assertEquals(NOME_STANZA_PARTENZA, this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiStanzaPresente() {
		Stanza destinazione = new Stanza("destinazione");
		this.partenza.impostaStanzaAdiacente("sud", destinazione);
		this.comandoVai.setParametro(NORD);
		this.comandoVai.esegue(partita);
		assertEquals(NOME_STANZA_PARTENZA, this.partita.getStanzaCorrente().getNome());
	}
}
