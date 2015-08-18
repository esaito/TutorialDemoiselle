package br.gov.serpro.inscricao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.Controller;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.serpro.inscricao.config.InscricaoConfig;
import br.gov.serpro.inscricao.entity.Aluno;
import br.gov.serpro.inscricao.exception.TurmaException;

@Controller
public class Turma {

	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;
	
	@Inject
	private InscricaoConfig config;

	private List<Aluno> alunosMatriculados = new ArrayList<Aluno>();
	//private List<String> alunosMatriculados = new ArrayList<String>();

	//public void matricular(String aluno) {
	public void matricular(Aluno aluno) {

		// if (estaMatriculado(aluno) || alunosMatriculados.size() == 5) {
		if (estaMatriculado(aluno) || 
			alunosMatriculados.size() == config.getCapacidadeTurma()) {
		
			throw new TurmaException();
		}

		alunosMatriculados.add(aluno);
		logger.info(bundle.getString("matricula.sucesso", aluno));
	}

	//public boolean estaMatriculado(String aluno) {
	public boolean estaMatriculado(Aluno aluno) {
		return alunosMatriculados.contains(aluno);
	}

	@ExceptionHandler
	public void tratar(TurmaException e) {
		logger.warning(bundle.getString("matricula.erro"));
		throw e;
	}

}