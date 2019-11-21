package model.dao;

import java.util.List;

import model.entities.Setor;

public interface SetorDao {

	public void insert(Setor setor);
	public List<Setor> listAllSetor();
}
