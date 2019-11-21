package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import db.DB;
import db.dbException;
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
			st = conn.prepareStatement("INSERT INTO setor (nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, setor.getNome());
			int teste = st.executeUpdate();
			
			if (teste > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					Alerts.showAlert("Sucesso", null, "Setor " + rs.getString(2) + " cadastrado com sucesso!", AlertType.CONFIRMATION);
				}
			}
			else {
				throw new dbException("Erro ao inserir dados no banco");
			}
		}
		catch (SQLException e) {
			throw new dbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

}
