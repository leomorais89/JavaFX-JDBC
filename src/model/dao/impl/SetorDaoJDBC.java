package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.SQLError;

import db.DB;
import db.dbException;
import db.dbIntegridadeException;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.dao.SetorDao;
import model.entities.Setor;

public class SetorDaoJDBC implements SetorDao {

	private Connection conn;
	
	public SetorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Setor setor) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO setor (nomeSetor) VALUES (?)");
			st.setString(1, setor.getNome());
			int teste = st.executeUpdate();
			
			if (teste > 0) {
				Alerts.showAlert("Sucesso", null, "Setor " + setor.getNome() + " cadastrado com sucesso!", AlertType.INFORMATION);
			}
		}
		catch (SQLException e) {
			throw new dbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Setor> listAllSetor() {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Setor> list = new ArrayList<Setor>();
		try {
			st = conn.prepareStatement("Select * from setor order by nomeSetor");
			rs = st.executeQuery();
			while (rs.next()) {
				Setor setor = new Setor();
				setor.setId(rs.getInt("idSetor"));
				setor.setNome(rs.getString("nomeSetor"));
				list.add(setor);
			}
			return list;
		}
		catch (SQLException e) {
			throw new dbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Setor setor) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update setor set nomeSetor = ? where idSetor = ?");
			st.setString(1, setor.getNome());
			st.setInt(2, setor.getId());
			int teste = st.executeUpdate();
			if (teste > 0) {
				Alerts.showAlert("Atualiza��o de Dados", null, "Setor " + setor.getNome() + " atualizado com sucesso!", AlertType.INFORMATION);
			}
			else {
				throw new dbException("Erro ao atualizar dados no banco");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new dbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void delSetor(Setor setor) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("delete from setor where idSetor = ?");
			st.setInt(1, setor.getId());
			int teste = st.executeUpdate();
			if (teste > 0) {
				Alerts.showAlert("Exclus�o de dados", null, "Setor " + setor.getNome() + " foi excluido com sucesso!", AlertType.INFORMATION);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new dbIntegridadeException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
}
