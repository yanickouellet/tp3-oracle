package com.dinfo.tp3.beans;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.dinfo.tp3.classes.BiMembres;
import com.dinfo.tp3.classes.MembreUtil;

@ManagedBean
@RequestScoped
public class ConnexionBean {
	private String login, motPasse, erreur;
	private MembreUtil membreUtil;
	
	public ConnexionBean() {
		membreUtil = new MembreUtil();
	}
    
    public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
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
  
    public String verifierConnexion() {
    	BiMembres membre = membreUtil.GetMembreAuthentifie(login, motPasse);
    	if (membre == null) {
    		this.setErreur("Login ou mot de passe incorrect.");
    	}
    	return "";
    }

}