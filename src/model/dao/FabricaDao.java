package model.dao;

import db.DB;
import model.dao.impl.SetorDaoJDBC;

public class FabricaDao {

	public static SetorDao criarSetorDao() {
		return new SetorDaoJDBC(DB.getConnection());
	}
}
