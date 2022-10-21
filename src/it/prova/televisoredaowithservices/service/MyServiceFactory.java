package it.prova.televisoredaowithservices.service;

import it.prova.televisoredaowithservices.dao.televisore.*;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreServiceImpl;

public class MyServiceFactory {
	
	public static TelevisoreService getUserServiceImpl() {
		TelevisoreService userService = new TelevisoreServiceImpl();
		userService.setTelevisoreDao(new TelevisoreDAOImpl()); //setTelevisoreDao
		return userService;
	}

}
