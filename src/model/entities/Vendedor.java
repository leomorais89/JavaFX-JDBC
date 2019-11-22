package model.entities;

import java.util.Date;

public class Vendedor {

	private Integer id;
	private String nome;
	private Date dataNascimento;
	private String email;
	private Double salario;
	private Setor setor;
	
	public Vendedor() {
		
	}

	public Vendedor(Integer id, String nome, Date dataNascimento, String email, Double salario, Setor setor) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.salario = salario;
		this.setor = setor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	@Override
	public String toString() {
		return "Vendedor [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", email=" + email
				+ ", salario=" + salario + ", setor=" + setor + "]";
	}
}
