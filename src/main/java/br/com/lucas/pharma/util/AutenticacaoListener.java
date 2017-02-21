/*package br.com.lucas.pharma.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.lucas.pharma.bean.AutenticacaoBean;
import br.com.lucas.pharma.domain.Usuario;

public class AutenticacaoListener implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();

		boolean paginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml");
		if(!paginaDeAutenticacao) {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");				
			if(autenticacaoBean == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			if(usuario == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}				
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {		

	}

	@Override
	public PhaseId getPhaseId() {		
		return PhaseId.RESTORE_VIEW;
	}

}
*/