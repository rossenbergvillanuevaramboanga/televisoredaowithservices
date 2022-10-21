package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.prova.televisoredaowithservices.dao.AbstractMySQLDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreDAOImpl extends AbstractMySQLDAO implements TelevisoreDAO {

	@Override
	public List<Televisore> list() throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Televisore> result = new ArrayList<Televisore>();

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from televisore")) {

			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();

				televisoreTemp.setMarca(rs.getString("marca"));
				televisoreTemp.setModello(rs.getString("modello"));
				televisoreTemp.setPollici(rs.getInt("pollici"));
				televisoreTemp.setDataproduzione(rs.getDate("dataproduzione"));
				televisoreTemp.setId(rs.getLong("id"));
				result.add(televisoreTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Televisore result = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {

					result = new Televisore();
					result.setMarca(rs.getString("marca"));
					result.setModello(rs.getString("modello"));
					result.setPollici(rs.getInt("pollici"));
					result.setDataproduzione(rs.getDate("dataproduzione"));
					result.setId(rs.getLong("id"));

				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE televisore SET marca=?, modello=?, pollici=?, dataproduzione=? where id=?;")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, new java.sql.Date(input.getDataproduzione().getTime()));
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection
				.prepareStatement("INSERT INTO televisore (marca, modello, pollici, dataproduzione) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, new java.sql.Date(input.getDataproduzione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM televisore WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Televisore> result = new ArrayList<Televisore>();

		String query = "select * from televisore where 1=1 ";
		if (input.getMarca() != null && !input.getMarca().isEmpty()) {
			query += " and marca like '" + input.getMarca() + "%' ";
		}

		if (input.getModello() != null && !input.getModello().isEmpty()) {
			query += " and modello like '" + input.getModello() + "%' ";
		}

		if (input.getPollici() != null && input.getPollici() != 0) {
			query += " and pollici = '" + input.getPollici() + "' ";
		}

		if (input.getDataproduzione() != null) {
			query += " and dataproduzione='" + new java.sql.Date(input.getDataproduzione().getTime()) + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setMarca(rs.getString("marca"));
				televisoreTemp.setModello(rs.getString("modello"));
				televisoreTemp.setPollici(rs.getInt("pollici"));
				televisoreTemp.setDataproduzione(rs.getDate("dataproduzione"));
				televisoreTemp.setId(rs.getLong("id"));
				result.add(televisoreTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		this.connection = connection;

	}

	@Override
	public List<Televisore> findAllTelevisoreWhereDataProduzioneBetween(Date inizioDate, Date fineDate)
			throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (inizioDate == null || fineDate == null)
			throw new Exception("Valore di input non ammesso.");
		
		ArrayList<Televisore> result = new ArrayList<Televisore>();

		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where dataproduzione between ? and ? ")) {
			ps.setDate(1, new java.sql.Date(inizioDate.getTime()));
			ps.setDate(2, new java.sql.Date(fineDate.getTime()));
			
			try (ResultSet rs = ps.executeQuery()) {
				
				while (rs.next()) {
					Televisore televisoreTemp = new Televisore();
					televisoreTemp.setMarca(rs.getString("marca"));
					televisoreTemp.setModello(rs.getString("modello"));
					televisoreTemp.setPollici(rs.getInt("pollici"));
					televisoreTemp.setDataproduzione(rs.getDate("dataproduzione"));
					televisoreTemp.setId(rs.getLong("id"));
					result.add(televisoreTemp);
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore findLargestTelevisore() throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		Televisore result = null;

		try (Statement ps = connection.createStatement(); 
				ResultSet rs = ps.executeQuery(
						"select * from televisore t inner join (select id, max(pollici) max from televisore group by id) b on b.max = t.pollici and b.id = t.id limit 1 ")) {

			if (rs.next()) {
				result = new Televisore();
				result.setMarca(rs.getString("marca"));
				result.setModello(rs.getString("modello"));
				result.setPollici(rs.getInt("pollici"));
				result.setDataproduzione(rs.getDate("dataproduzione"));
				result.setId(rs.getLong("id"));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<String> findAllMarcaTelevisoreLastSixMonth() throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		List<String> result = new ArrayList<String>();
		String marcaTemp = null;

		try (Statement ps = connection.createStatement(); 
				ResultSet rs = ps.executeQuery(
						"select distinct marca as m from televisore where dataproduzione >= date_sub(current_date(), interval 6 month)")) {

			while (rs.next()) {
				marcaTemp = rs.getString("m");
				result.add(marcaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
