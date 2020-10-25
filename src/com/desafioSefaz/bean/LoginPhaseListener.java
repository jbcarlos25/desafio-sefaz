package com.desafioSefaz.bean;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import com.desafioSefaz.entities.Usuario;



public class LoginPhaseListener implements PhaseListener{

	private static final long serialVersionUID = 1L;
	private FacesContext facesContext;

	    @Override
	    public void afterPhase(PhaseEvent event) {
	        facesContext = event.getFacesContext();
	        String viewId = facesContext.getViewRoot().getViewId();
	        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
	        boolean paginaLogin = viewId.lastIndexOf("login") > -1;
	        if (existeUsuarioLogado() && paginaLogin) {
	            nh.handleNavigation(facesContext, null, "home");
	        } else if (!existeUsuarioLogado() && !paginaLogin) {
	            nh.handleNavigation(facesContext, null, "login");
	        }
	    }

	    public boolean existeUsuarioLogado() {
	        return (((Usuario) getAtributoSessao("usuario")) != null);
	    }

	    public Object getAtributoSessao(String attributeName) {
	        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	        if (session != null) {
	            return session.getAttribute(attributeName);
	        }
	        return null;
	    }

	    @Override
	    public void beforePhase(PhaseEvent event) {
	        
	    }

	    @Override
	    public PhaseId getPhaseId() {
	        return PhaseId.RESTORE_VIEW;
	    }

	}
