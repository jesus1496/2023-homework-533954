package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comando.ComandoVai;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoVaiTest {

	private Stanza s1;
	private Stanza s2;
	private Partita partita;
	private ComandoVai comandoVai;
	private Scanner scanner;
	private Labirinto labirinto;
	
	@Before
	public void setUp() throws Exception{
		this.comandoVai = new ComandoVai();
		
		this.comandoVai.setIo(new IOConsole(scanner));
		this.partita = new Partita(labirinto);
		this.s1 = new Stanza("aula 1");
		this.s2 = new Stanza("aula 2");
	}
	
	@Test
	public void testVaiNull() {
		this.partita.setStanzaCorrente(s1);
		this.comandoVai.esegui(partita);
		assertEquals(s1, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testVaiDirezioneInesistente() {
		this.partita.setStanzaCorrente(s1);
		this.s1.impostaStanzaAdiacente(Direzione.sud_ovest, s2);
		this.comandoVai.setParametro("sud-ovest");
		this.comandoVai.esegui(partita);
		assertEquals(s2, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testPartitaConComandoVai() throws Exception {
		String[] righeDaLeggere = {"vai nord", "fine"};
		IOSimulator io = Fixture.creaSimulazionePartitaEGioca(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Biblioteca", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Hai vinto!", io.nextMessaggio());
	}
}
