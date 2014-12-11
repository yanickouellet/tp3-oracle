package com.dinfo.tp3.beans;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.dinfo.tp3.classes.BiMembres;
import com.dinfo.tp3.classes.MembreUtil;

@ManagedBean
@SessionScoped
public class ConnexionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String login, motPasse;
	private int no;
	private MembreUtil membreUtil;
	
	public ConnexionBean() {
		membreUtil = new MembreUtil();
		setNo(-1);
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
    	BiMembres membre = membreUtil.GetMembreAuthentifie(login, motPasse);
    	if (membre == null) {
    		FacesContext.getCurrentInstance()
    			.addMessage("login", new FacesMessage("Login ou mot de passe incorrect"));
    	} else {
    		this.setNo(membre.getNoMembre());
    	}
    	return "";
    }

}