package model.services;

import java.util.List;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entities.Vendedor;

public class VendedorService {

	private VendedorDao dao = FabricaDao.criarVendedorDao();
	
	public List<Vendedor> todosVendedores() {
		return dao.todosVendedores();
	}
}