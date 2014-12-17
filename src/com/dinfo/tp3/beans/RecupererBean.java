package com.dinfo.tp3.beans;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.dinfo.tp3.classes.BiMembres;
import com.dinfo.tp3.classes.MembreUtil;

@ManagedBean(eager=true)
@ViewScoped
public class RecupererBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String login, question, reponse, motPasse;
	private int no;
	private MembreUtil membreUtil;
	
	public RecupererBean() {
		membreUtil = new MembreUtil();
		setNo(-1);
	}
    
	public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.trim();
    }
  
    public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String recuperer() {
		BiMembres membre = membreUtil.getMembreParLogin(login);
		
		if(membre == null) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nom d'utilisateur introuvable", null));
			return "";
		}
		
		setQuestion(membre.getQuestionSecrete());
		setNo(membre.getNoMembre());
		
		return "";
	}
	
	public String valider() {
		BiMembres membre = membreUtil.getMembre(no);
		System.out.println("asdasd");
		if(membre == null) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Une erreur s'est produite", null));
			return "";
		}
		
		if(!membre.getReponseSecrete().equals(reponse)) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La réponse est incorrecte.", null));
			return "";
		}
		
		if(motPasse.trim().equals("")) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le mot de passe doit être spécifié.", null));
			return "";
		}
		
		setQuestion("");
		setNo(-1);
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Le mot de passe a été réinitialisé", null));
		membreUtil.resetMotPasse(membre, motPasse);
		
		return "index";
	}
}