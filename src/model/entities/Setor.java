package model.entities;

public class Setor {

	private Integer Id;
	private String Nome;
	
	public Setor() {
		
	}

	public Setor(Integer id, String nome) {
		super();
		Id = id;
		Nome = nome;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer id) {
		this.Id = id;
	}

	@Override
	public String toString() {
		return Nome;
	}
}
