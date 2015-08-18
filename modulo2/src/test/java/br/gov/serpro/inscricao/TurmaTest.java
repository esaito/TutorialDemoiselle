package br.gov.serpro.inscricao;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class TurmaTest {

	@Inject
	private Turma turma;

	@Test
	public void matricularAlunoComSucesso() {
		turma.matricular("Santos Dumont");
		Assert.assertTrue(turma.estaMatriculado("Santos Dumont"));
	}

	@Test(expected = RuntimeException.class)
	public void falhaAoTentarMatricularAlunoDuplicado() {
		turma.matricular("Orville Wright");
		turma.matricular("Orville Wright");
	}

	@Test(expected = RuntimeException.class)
	public void falhaAoTentarMatricularAlunoNaTurmaCheia() {
		for (int i = 1; i <= 5; i++) {
			turma.matricular("Aluno " + String.valueOf(i).toString());
		}
		turma.matricular("Aluno 6");
	}

}
