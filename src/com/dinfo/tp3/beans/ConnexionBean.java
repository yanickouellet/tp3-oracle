package com.dinfo.tp3.beans;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.dinfo.tp3.classes.BiMembres;

@ManagedBean
@RequestScoped
public class ConnexionBean {
	private String login, motPasse;
    
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
    	return "";
    }

}