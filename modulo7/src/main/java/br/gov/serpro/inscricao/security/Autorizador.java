package br.gov.serpro.inscricao.security;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.AuthorizationException;
import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class Autorizador implements Authorizer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SecurityContext securityContext;
	
	@Inject
	private ResourceBundle bundle;

	@Override
	public boolean hasPermission(String res, String op) throws Exception {
		
		boolean autorizado = false;
		if (securityContext != null){
			if (securityContext.isLoggedIn()){
				String usr=securityContext.getUser().getName();
				if (usr.equals("secretaria") && res.equals("aluno") && (op.equals("consultar") || op.equals("matricular"))){
					autorizado = true;
				}
				else{
					autorizado = false;
					throw new AuthorizationException(bundle.getString("usuarioNaoAutorizado",res,op));
				}
			}
		}
		return autorizado;
	}

	@Override
	public boolean hasRole(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
