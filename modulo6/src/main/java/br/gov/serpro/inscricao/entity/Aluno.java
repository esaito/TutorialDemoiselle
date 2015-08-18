package br.gov.serpro.inscricao.entity;

import javax.persistence.*;

@Entity
public class Aluno {
	
	@Id
	@GeneratedValue
	private Integer matricula;
	
	private String nome;

	public Aluno() {
	}

	public Aluno(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	@Override
	public boolean equals(Object outro) {
		return ((Aluno) outro).nome.equals(this.nome);
	}
}
