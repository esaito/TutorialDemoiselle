package br.gov.serpro.inscricao.entity;

public class Aluno {
	
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
	

	@Override
	public boolean equals(Object outro) {
		return ((Aluno) outro).nome.equals(this.nome);
	}


}
