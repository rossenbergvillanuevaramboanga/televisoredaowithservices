package it.prova.televisoredaowithservices.dao.televisore;

import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.IBaseDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreDAO extends IBaseDAO<Televisore>{
	
	//tutti i televisori prodotti in un intervallo di date
	List<Televisore> findAllTelevisoreWhereDataProduzioneBetween(Date inizioDate, Date fineDate) throws Exception;
	
	//televisore più grande
	Televisore findLargestTelevisore() throws Exception; 
	
	//la lista delle marche di televisori prodotti negli ultimi sei mesi 
	List<String> findAllMarcaTelevisoreLastSixMonth() throws Exception;

}
