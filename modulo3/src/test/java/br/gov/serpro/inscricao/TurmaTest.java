package br.gov.serpro.inscricao;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.serpro.inscricao.entity.Aluno;
import br.gov.serpro.inscricao.exception.TurmaException;

@RunWith(DemoiselleRunner.class)
public class TurmaTest {

	@Inject
	private Turma turma;

	@Test
	public void matricularAlunoComSucesso() {
		Aluno aluno = new Aluno("Santos Dumont");

		turma.matricular(aluno);
		//turma.matricular("Santos Dumont");
		Assert.assertTrue(turma.estaMatriculado(aluno));
		//Assert.assertTrue(turma.estaMatriculado("Santos Dumont"));
	}

	@Test(expected = TurmaException.class)
	public void falhaAoTentarMatricularAlunoDuplicado() {
		turma.matricular(new Aluno("Orville Wright"));
		turma.matricular(new Aluno("Orville Wright"));
		//turma.matricular("Orville Wright");
		//turma.matricular("Orville Wright");
	}

	@Test(expected = TurmaException.class)
	public void falhaAoTentarMatricularAlunoNaTurmaCheia() {
		for (int i = 1; i <= 5; i++) {			
			turma.matricular(new Aluno("Aluno " + String.valueOf(i).toString()));
			//turma.matricular("Aluno " + String.valueOf(i).toString());
		}
		
		turma.matricular(new Aluno("Aluno 6"));
		//turma.matricular("Aluno 6");
	}

}
