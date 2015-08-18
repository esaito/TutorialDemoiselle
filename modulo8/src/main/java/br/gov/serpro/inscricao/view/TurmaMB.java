package br.gov.serpro.inscricao.view;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.serpro.inscricao.business.TurmaBC;
import br.gov.serpro.inscricao.entity.Aluno;

import java.io.Serializable;

@ViewController
public class TurmaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaBC bc;

	@Inject
	private FacesContext facesContext;
	
	private String nomeAluno;

	public List<Aluno> getAlunosMatriculados() {
		return bc.obterAlunosMatriculados();
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public void matricular() {
		bc.matricular(new Aluno(nomeAluno));
		facesContext.addMessage("sucesso", new FacesMessage("Cadastro realizado com sucesso"));
	}
}