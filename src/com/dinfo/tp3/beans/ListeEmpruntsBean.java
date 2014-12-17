package com.dinfo.tp3.beans; 

import java.util.List; 

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.dinfo.tp3.classes.ArticleUtil;
import com.dinfo.tp3.classes.BiArticles;
import com.dinfo.tp3.classes.BiEmprunts;

@ManagedBean
@RequestScoped
public class ListeEmpruntsBean {
	private List<BiEmprunts> liste;
	private ArticleUtil util;
	
	public ListeEmpruntsBean() { 
		util = new ArticleUtil();
		refreshData();
	}

	public List<BiEmprunts> getListe() {
		return liste;
	}

	public void setListe(List<BiEmprunts> liste) {
		this.liste = liste;
	}
	
	public String retour(BiEmprunts emprunt) {
		util.retour(emprunt);
		refreshData();
		
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "L'article a été retourné.", null));
		return "";
	}
	
	public String payer(BiEmprunts emprunt) {
		util.payer(emprunt);
		refreshData();
		
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "L'amende a bien été payée.", null));
		return "";
	}
	
	private void refreshData() {
		setListe(util.getEmprunts());
	}
	
	
}