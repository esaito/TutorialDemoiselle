package br.gov.serpro.inscricao;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.Controller;
import br.gov.frameworkdemoiselle.transaction.Transactional;
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

	@Inject
	private EntityManager em;

	@Transactional
	public void matricular(Aluno aluno) {

		if (estaMatriculado(aluno)
				|| obterAlunosMatriculados().size() == config
						.getCapacidadeTurma()) {
			throw new TurmaException();
		}
		em.persist(aluno);
		logger.info(bundle.getString("matricula.sucesso", aluno.getNome()));
	}

	public boolean estaMatriculado(Aluno aluno) {
		return obterAlunosMatriculados().contains(aluno);
	}

	@SuppressWarnings("unchecked")
	private List<Aluno> obterAlunosMatriculados() {
		return em.createQuery("select a from Aluno a").getResultList();
	}

	@ExceptionHandler
	public void tratar(TurmaException e) {
		logger.warning(bundle.getString("matricula.erro"));
		throw e;
	}

	@Transactional
	public void zeraBase() {
		List<Aluno> alunos = obterAlunosMatriculados();
		if (!alunos.isEmpty()) {
			for (Aluno a : alunos) {
				em.remove(a);				
			}
		}
	}

}