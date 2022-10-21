package it.prova.televisoredaowithservices.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.model.Televisore;
import it.prova.televisoredaowithservices.service.MyServiceFactory;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;

public class TestTelevisore {

	public static void main(String[] args) {

		TelevisoreService televisoreService = MyServiceFactory.getUserServiceImpl();

		try {

			// TestCRUDTelevisore - Create, Read, Update and Delete
			// TestFindByExampleTelevisore
			// TestFindAllTelevisoreWhereDataProduzioneBetweenTelevisore
			// TestFindLargestTelevisore
			// TestFindAllMarcaTelevisoreLastSixMonth

			testCRUDTelevisore(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi. \n");

			testFindByExampleTelevisore(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi. \n");

			testFindAllTelevisoreWhereDataProduzioneBetweenTelevisore(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi. \n");

			testFindLargestTelevisore(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi. \n");

			testFindAllMarcaTelevisoreLastSixMonth(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi. \n");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private static void testFindAllMarcaTelevisoreLastSixMonth(TelevisoreService televisoreService) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testFindAllMarcaTelevisoreLastSixMonth inizio.............");

		// Creazione Televisore Grande
		Televisore testTelevisore = new Televisore("Samsung", "Neo QLED 8K", 70,
				new Date());
		if (televisoreService.inserisciNuovo(testTelevisore) != 1)
			throw new RuntimeException("testFindAllMarcaTelevisoreLastSixMonth: inserisciNuovo FAILED ");
		List<Televisore> risultati = televisoreService.listAll();
		if (risultati.size() != 1)
			throw new RuntimeException("testFindAllMarcaTelevisoreLastSixMonth: testListAll FAILED ");
		testTelevisore = risultati.get(0);

		// Creazione Televisore Piccolo
		Televisore testTelevisorePiccolo = new Televisore("Sony", "Bravia 8K", 50,
				new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000"));
		if (televisoreService.inserisciNuovo(testTelevisorePiccolo) != 1)
			throw new RuntimeException("testFindAllMarcaTelevisoreLastSixMonth: inserisciNuovo FAILED ");
		List<Televisore> risultatiPiccolo = televisoreService.listAll();
		if (risultatiPiccolo.size() != 2)
			throw new RuntimeException("testFindAllMarcaTelevisoreLastSixMonth: testListAll FAILED ");
		testTelevisorePiccolo = risultatiPiccolo.get(1);

		// PROPER TEST
		if (televisoreService.cercaTutteMarcheUltimiSeiMesi().size() != 1)
			throw new RuntimeException("testFindAllMarcaTelevisoreLastSixMonth: FAILED ");

		// Remove
		if (televisoreService.rimuovi(testTelevisore) != 1)
			throw new RuntimeException("testFindAllMarcaTelevisoreLastSixMonth: testRimuovi FAILED ");
		
		if (televisoreService.rimuovi(testTelevisorePiccolo) != 1)
			throw new RuntimeException("testFindAllMarcaTelevisoreLastSixMonth: testRimuovi FAILED ");

		System.out.println(".......testFindAllMarcaTelevisoreLastSixMonth PASSED.............");

	}

	private static void testFindLargestTelevisore(TelevisoreService televisoreService) throws Exception {
		// TODO Auto-generated method stub

		System.out.println(".......testFindLargestTelevisore inizio.............");

		// Creazione Televisore Grande
		Televisore testTelevisore = new Televisore("Samsung", "Neo QLED 8K", 70,
				new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000"));
		if (televisoreService.inserisciNuovo(testTelevisore) != 1)
			throw new RuntimeException("testFindLargestTelevisore: inserisciNuovo FAILED ");
		List<Televisore> risultati = televisoreService.listAll();
		if (risultati.size() != 1)
			throw new RuntimeException("testFindLargestTelevisore: testListAll FAILED ");
		testTelevisore = risultati.get(0);

		// Creazione Televisore Piccolo
		Televisore testTelevisorePiccolo = new Televisore("Samsung", "Neo QLED 8K", 50,
				new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000"));
		if (televisoreService.inserisciNuovo(testTelevisorePiccolo) != 1)
			throw new RuntimeException("testFindLargestTelevisore: inserisciNuovo FAILED ");
		List<Televisore> risultatiPiccolo = televisoreService.listAll();
		if (risultatiPiccolo.size() != 2)
			throw new RuntimeException("testFindLargestTelevisore: testListAll FAILED ");
		testTelevisorePiccolo = risultatiPiccolo.get(1);

		// PROPER TEST
		if (televisoreService.cercaTelevisorePiuGrande().equals(testTelevisore))
			throw new RuntimeException("testFindLargestTelevisore: FAILED ");

		// Remove
		if (televisoreService.rimuovi(testTelevisore) != 1)
			throw new RuntimeException("testFindLargestTelevisore: testRimuovi FAILED ");
		
		if (televisoreService.rimuovi(testTelevisorePiccolo) != 1)
			throw new RuntimeException("testFindLargestTelevisore: testRimuovi FAILED ");

		System.out.println(".......testFindLargestTelevisore PASSED.............");

	}

	private static void testFindAllTelevisoreWhereDataProduzioneBetweenTelevisore(TelevisoreService televisoreService)
			throws Exception {
		// TODO Auto-generated method stub

		System.out.println(".......testFindAllTelevisoreWhereDataProduzioneBetweenTelevisore inizio.............");

		Televisore testTelevisore = new Televisore("Samsung", "Neo QLED 8K", 70,
				new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000"));

		if (televisoreService.inserisciNuovo(testTelevisore) != 1)
			throw new RuntimeException("testFindByExampleTelevisore: inserisciNuovo FAILED ");

		List<Televisore> risultati = televisoreService.listAll();
		if (risultati.size() != 1)
			throw new RuntimeException("testFindByExampleTelevisore: testListAll FAILED ");
		testTelevisore = risultati.get(0);

		// PROPER TEST
		Date inizioDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-04-1976");
		Date fineDate = new Date();
		if (televisoreService.cercaTuttiQuelliConDatProduzioneTra(inizioDate, fineDate).size() != 1)
			throw new RuntimeException("testFindAllTelevisoreWhereDataProduzioneBetweenTelevisore: FAILED ");

		// Remove
		if (televisoreService.rimuovi(testTelevisore) != 1)
			throw new RuntimeException("testFindByExampleTelevisore: testRimuovi FAILED ");

		System.out.println(".......testFindAllTelevisoreWhereDataProduzioneBetweenTelevisore PASSED.............");

	}

	private static void testFindByExampleTelevisore(TelevisoreService televisoreService) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testFindByExampleTelevisore inizio.............");

		Televisore testTelevisore = new Televisore("Samsung", "Neo QLED 8K", 70, new Date());

		if (televisoreService.inserisciNuovo(testTelevisore) != 1)
			throw new RuntimeException("testFindByExampleTelevisore: inserisciNuovo FAILED ");

		List<Televisore> risultati = televisoreService.listAll();
		if (risultati.size() != 1)
			throw new RuntimeException("testFindByExampleTelevisore: testListAll FAILED ");
		testTelevisore = risultati.get(0);

		Televisore exampleTelevisore = new Televisore();
		exampleTelevisore.setMarca("S");
		exampleTelevisore.setPollici(70);

		// PROPER TEST
		if (televisoreService.findByExample(exampleTelevisore).size() != 1)
			throw new RuntimeException("testFindByExampleTelevisore: FAILED ");

		// Remove
		if (televisoreService.rimuovi(testTelevisore) != 1)
			throw new RuntimeException("testFindByExampleTelevisore: testRimuovi FAILED ");

		System.out.println(".......testFindByExampleTelevisore PASSED.............");

	}

	private static void testCRUDTelevisore(TelevisoreService televisoreService) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......TestCRUDTelevisore inizio.............");

		Televisore testTelevisore = new Televisore("Samsung", "Neo QLED 8K", 70, new Date());

		if (televisoreService.inserisciNuovo(testTelevisore) != 1)
			throw new RuntimeException("TestCRUDTelevisore: inserisciNuovo FAILED ");

		List<Televisore> risultati = televisoreService.listAll();
		if (risultati.size() != 1)
			throw new RuntimeException("TestCRUDTelevisore: testListAll FAILED ");
		testTelevisore = risultati.get(0);

		// Update
		testTelevisore.setPollici(85);
		if (televisoreService.aggiorna(testTelevisore) != 1)
			throw new RuntimeException("TestCRUDTelevisore: testAggiorna FAILED ");

		// Remove
		if (televisoreService.rimuovi(testTelevisore) != 1)
			throw new RuntimeException("TestCRUDTelevisore: testRimuovi FAILED ");

		System.out.println(".......TestCRUDTelevisore PASSED.............");

	}

}
