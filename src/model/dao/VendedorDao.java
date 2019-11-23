package model.dao;

import java.util.List;

import model.entities.Vendedor;

public interface VendedorDao {

	public List<Vendedor> todosVendedores();
	public void insert(Vendedor vendedor);
	public void update(Vendedor vendedor);
	public void delVendedor(Vendedor vendedor);
}
