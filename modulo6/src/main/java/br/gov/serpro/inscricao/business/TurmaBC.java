package br.gov.serpro.inscricao.business;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.serpro.inscricao.config.InscricaoConfig;
import br.gov.serpro.inscricao.entity.Aluno;
import br.gov.serpro.inscricao.exception.TurmaException;
//import br.gov.serpro.inscricao.persistence.AlunoDAO;

@BusinessController
public class TurmaBC implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	@Inject
	private InscricaoConfig config;

	//@Inject 
	//private AlunoDAO alunoDAO;
	
	@Inject 
	private AlunoBC alunoBC;
	
//	@Inject
//	private EntityManager em;

	@Startup
	public void iniciar(){
		logger.info("Iniciando ...");
	}
	
	
	@Transactional
	@RequiredPermission(resource="aluno", operation="matricular")
	public void matricular(Aluno aluno) {

		if (estaMatriculado(aluno)
				|| obterAlunosMatriculados().size() == config
						.getCapacidadeTurma()) {
			throw new TurmaException();
		}
		//em.persist(aluno);
		//alunoDAO.insert(aluno);
		alunoBC.insert(aluno);
		logger.info(bundle.getString("matricula.sucesso", aluno.getNome()));
	}
	
	@RequiredPermission(resource="aluno", operation="consultar")
	public boolean estaMatriculado(Aluno aluno) {
		return obterAlunosMatriculados().contains(aluno);
	}

	//@SuppressWarnings("unchecked")
	private List<Aluno> obterAlunosMatriculados() {
		// return em.createQuery("select a from Aluno a").getResultList();
		//return alunoDAO.findAll();
		return alunoBC.findAll();
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
				//em.remove(a);
				//alunoDAO.remove(a);
				//alunoDAO.delete(a.getMatricula());
				alunoBC.delete(a.getMatricula());;
			}
		}
	}

}