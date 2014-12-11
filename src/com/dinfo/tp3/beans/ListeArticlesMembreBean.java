package com.dinfo.tp3.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.dinfo.tp3.classes.ArticlesUtil;
import com.dinfo.tp3.classes.BiArticles;

@ManagedBean
@RequestScoped
public class ListeArticlesMembreBean {
	private List<BiArticles> liste;
	
	public ListeArticlesMembreBean() {
		ArticlesUtil util = new ArticlesUtil();
		setListe(util.GetArticlesParMembre(1));
	}

	public List<BiArticles> getListe() {
		return liste;
	}

	public void setListe(List<BiArticles> liste) {
		this.liste = liste;
	}
	
	
}