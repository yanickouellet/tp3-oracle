package com.dinfo.tp3.beans;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.dinfo.tp3.classes.BiMembres;
import com.dinfo.tp3.classes.MembreUtil;

@ManagedBean(eager=true)
@SessionScoped
public class ConnexionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String login, motPasse;
	private int no;
	private boolean admin;
	private MembreUtil membreUtil;
	
	public ConnexionBean() {
		membreUtil = new MembreUtil();
		setNo(-1);
		setAdmin(false);
	}
    
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.trim();
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }
  
    public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String verifierConnexion() {
    	BiMembres membre = membreUtil.getMembreAuthentifie(login, motPasse);
    	if (membre == null) {
    		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou mot de passe incorrect", null));
    		return "index";
    	} else {
    		this.setNo(membre.getNoMembre());
    		if(membre.getTypeMembre() == '2') {
    			this.setAdmin(true);
    			return "location";
    		}
    		return "articles-membres";
    	}
    }
	
	public String deconnexion() {
		setLogin("");
		setAdmin(false);
		setNo(-1);
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Vous avez été déconnecté.", null));
		
		return "index";
	}
	
	public boolean estConnecte() {
		return getNo() > 0;
	}

}