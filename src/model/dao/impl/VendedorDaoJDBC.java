package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.dbException;
import model.dao.VendedorDao;
import model.entities.Setor;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	Connection conn = null;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Vendedor> todosVendedores() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select v.*, s.nomeSetor from vendedor as v inner join setor as s on (v.idSetor = s.idSetor)");
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<Vendedor>();
			Map<Integer, Setor> map = new HashMap<>();
			
			while (rs.next()) {
				Setor setor = map.get(rs.getInt("idSetor"));
				if (setor == null) {
					setor = new Setor();
					setor.setId(rs.getInt("idSetor"));
					setor.setNome(rs.getString("nomeSetor"));
					map.put(rs.getInt("idSetor"), setor);
				}
				Vendedor vendedor = new Vendedor();
				vendedor.setId(rs.getInt("idVendedor"));
				vendedor.setNome(rs.getString("nomeVendedor"));
				vendedor.setDataNascimento(new java.util.Date(rs.getTimestamp("dataNascimento").getTime()));
				vendedor.setEmail(rs.getString("email"));
				vendedor.setSalario(rs.getDouble("salario"));
				vendedor.setSetor(setor);
				list.add(vendedor);
			}
			return list;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new dbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
}
