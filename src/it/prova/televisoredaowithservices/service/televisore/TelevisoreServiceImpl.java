package it.prova.televisoredaowithservices.service.televisore;

import java.sql.Connection;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import it.prova.televisoredaowithservices.connection.MyConnection;
import it.prova.televisoredaowithservices.dao.Constants;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreServiceImpl implements TelevisoreService {

	private TelevisoreDAO televisoreDAO;

	@Override
	public void setTelevisoreDao(TelevisoreDAO televisoreDAO) {
		// TODO Auto-generated method stub
		this.televisoreDAO = televisoreDAO;

	}

	@Override
	public List<Televisore> listAll() throws Exception {
		// TODO Auto-generated method stub
		List<Televisore> result = new ArrayList<Televisore>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);
			result = televisoreDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore findById(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		Televisore result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);
			result = televisoreDAO.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);
			result = televisoreDAO.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);
			result = televisoreDAO.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);
			result = televisoreDAO.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		// TODO Auto-generated method stub

		List<Televisore> result = new ArrayList<Televisore>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);
			result = televisoreDAO.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> cercaTuttiQuelliConDatProduzioneTra(Date inizioDate, Date fineDate) throws Exception {
		// TODO Auto-generated method stub
		List<Televisore> result = new ArrayList<Televisore>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);
			result = televisoreDAO.findAllTelevisoreWhereDataProduzioneBetween(inizioDate,fineDate);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore cercaTelevisorePiuGrande() throws Exception {
		// TODO Auto-generated method stub
		Televisore result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);
			result = televisoreDAO.findLargestTelevisore();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<String> cercaTutteMarcheUltimiSeiMesi() throws Exception {
		// TODO Auto-generated method stub
		List<String> result = new ArrayList<String>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDAO.setConnection(connection);
			result = televisoreDAO.findAllMarcaTelevisoreLastSixMonth();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
