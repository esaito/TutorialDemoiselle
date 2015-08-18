package br.gov.serpro.inscricao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class Turma {

	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	private List<String> alunosMatriculados = new ArrayList<String>();

	public void matricular(String aluno) {

		if (estaMatriculado(aluno) || alunosMatriculados.size() == 5) {
			throw new RuntimeException();
		}

		alunosMatriculados.add(aluno);
		// logger.info("Aluno matriculado com sucesso");
		// logger.info(bundle.getString("matricula.sucesso"));
		logger.info(bundle.getString("matricula.sucesso", aluno));
	}

	public boolean estaMatriculado(String aluno) {
		return alunosMatriculados.contains(aluno);
	}

}