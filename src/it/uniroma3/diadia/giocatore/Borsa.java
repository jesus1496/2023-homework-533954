package it.uniroma3.diadia.giocatore;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String,Attrezzo> nome2attrezzo;
	private int pesoMax;
	private int pesoAttuale;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.nome2attrezzo = new HashMap<>();
		this.pesoAttuale = 0;
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.nome2attrezzo.put(attrezzo.getNome(), attrezzo);
		this.pesoAttuale += attrezzo.getPeso();
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.nome2attrezzo.get(nomeAttrezzo);
	}
	
	public int getPeso() {
		return this.pesoAttuale;
	}
	
	public boolean isEmpty() {
		return this.nome2attrezzo.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if(this.nome2attrezzo.containsKey(nomeAttrezzo)) {
			this.pesoAttuale = this.pesoAttuale - this.nome2attrezzo.get(nomeAttrezzo).getPeso();
		}
		return this.nome2attrezzo.remove(nomeAttrezzo);
	}
	
	/* Attrezzo a  = null;
	 * int i = 0;
	 * while( i< this.attrezzi.length && a == null){
	 * 		if(this.attrezzi[i] != null && this.attrezzi[i].getNome().equals(nomeAttrezzo));	 * 		
	 * 		a = this.attrezzi[i];
	 * 		this.attrezzi[i] = null;
	 * 		i++
	 * }
	 */
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		 	if (!this.isEmpty()) {
		 		s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
		 		s.append(this.nome2attrezzo.values().toString());
		 	}
		 	else {
		 		s.append("Borsa vuota");
		 	}
		 	return s.toString();
		}
	
	/*
	 * Restituisce la lista degli attrezzi nella borsa ordinati per peso e quindi, a parità di peso, per nome
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> attrezziPerPeso = new LinkedList<Attrezzo>(this.nome2attrezzo.values());
		Collections.sort(attrezziPerPeso, new ComparatoreAttrezziPerPeso());
		return attrezziPerPeso;
	}
	
	/*
	 * restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<>(this.nome2attrezzo.values());
	}
	
	/*
	 * Restituisce una mappa che associa un intero con l'insieme degli attrezzi di tale peso: tutti gli attrezzi dell'insieme che figura come valore
	 * hanno lo stesso peso pari all'intero che figura come chiave
	 */
	public Map<Integer, Set<Attrezzo>> getContenutoRaggrupatoPerPeso(){
		Map<Integer, Set<Attrezzo>> mappa = new TreeMap<>();
		for(Attrezzo attrezzo : this.nome2attrezzo.values()) {
			Set<Attrezzo> attrezziPeso = mappa.get(attrezzo.getPeso());
			if(attrezziPeso == null) {
				attrezziPeso = new HashSet<>();
				mappa.put(attrezzo.getPeso(), attrezziPeso);
			}
			attrezziPeso.add(attrezzo);
		}
		return mappa;
	}
	
	/*
	 * Restituisce l'insieme gli attrezzi nella borsa ordinati per peso e quindi, a parità di peso, per nome
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> attrezziPerPeso = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		attrezziPerPeso.addAll(this.nome2attrezzo.values());
		return attrezziPerPeso;
	}
}
