package model.dao;

import db.DB;
import model.dao.impl.SetorDaoJDBC;
import model.dao.impl.VendedorDaoJDBC;

public class FabricaDao {

	public static SetorDao criarSetorDao() {
		return new SetorDaoJDBC(DB.getConnection());
	}
	
	public static VendedorDao criarVendedorDao() {
		return new VendedorDaoJDBC(DB.getConnection());
	}
}
