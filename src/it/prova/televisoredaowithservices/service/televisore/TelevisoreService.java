package it.prova.televisoredaowithservices.service.televisore;

import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreService {
	
	//Injection
	
	public void setTelevisoreDao(TelevisoreDAO televisoreDAO);
	
	//CRUD
	public List<Televisore> listAll() throws Exception;
	
	public Televisore findById(Long idInput) throws Exception;

	public int aggiorna(Televisore input) throws Exception;

	public int inserisciNuovo(Televisore input) throws Exception;

	public int rimuovi(Televisore input) throws Exception;

	public List<Televisore> findByExample(Televisore input) throws Exception;
	
	// Other Methods

	//List<Televisore> findAllTelevisoreWhereDataProduzioneBetween(Date inizioDate, Date fineDate) throws Exception;
	//Televisore findLargestTelevisore() throws Exception; 
	//List<String> findAllMarcaTelevisoreLastSixMonth() throws Exception;
	
	public List<Televisore> cercaTuttiQuelliConDatProduzioneTra(Date inizioDate, Date fineDate) throws Exception;
	public Televisore cercaTelevisorePiuGrande() throws Exception;
	public List<String> cercaTutteMarcheUltimiSeiMesi() throws Exception;
	

}
