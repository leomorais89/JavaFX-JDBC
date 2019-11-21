package model.services;

import java.util.List;

import model.dao.FabricaDao;
import model.dao.SetorDao;
import model.entities.Setor;

public class SetorService {

	private SetorDao dao = FabricaDao.criarSetorDao();
	
	public void saveOrUpdate(Setor setor) {
		dao.insert(setor);
	}
	
	public List<Setor> todosSetores() {
		return dao.listAllSetor();
	}
}
