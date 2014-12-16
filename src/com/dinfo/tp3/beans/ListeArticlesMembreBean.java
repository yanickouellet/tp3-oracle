package com.dinfo.tp3.beans; 

import java.util.List; 

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.dinfo.tp3.classes.ArticleUtil;
import com.dinfo.tp3.classes.BiArticles;

@ManagedBean
@RequestScoped
public class ListeArticlesMembreBean {
	private List<BiArticles> liste;
	@ManagedProperty(value="#{connexionBean}")
	private ConnexionBean connexionBean;
	
	@javax.annotation.PostConstruct
	public void PostConstruct() { 
		ArticleUtil util = new ArticleUtil(); 
		if(connexionBean != null) { 
			setListe(util.GetArticlesParMembre(connexionBean.getNo()));
		}
	}

	public void setConnexionBean(ConnexionBean connexionBean) {
		this.connexionBean = connexionBean;
	}


	public ConnexionBean getConnexionBean() {
		return connexionBean;
	}

	public List<BiArticles> getListe() {
		return liste;
	}

	public void setListe(List<BiArticles> liste) {
		this.liste = liste;
	}
	
	
}