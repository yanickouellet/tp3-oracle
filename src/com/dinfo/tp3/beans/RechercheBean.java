package com.dinfo.tp3.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.dinfo.tp3.classes.BiArticles;
import com.dinfo.tp3.classes.ArticleUtil;

@ManagedBean
@RequestScoped
public class RechercheBean {
	private String recherche, message;
	private List<BiArticles> resultats;
	private ArticleUtil articleUtil;
	private String isbn;
	
	public RechercheBean() {
		articleUtil = new ArticleUtil();
	}

	public String getRecherche() {
		return recherche;
	}

	public void setRecherche(String recherche) {
		this.recherche = recherche;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BiArticles> getResultats() {
		return resultats;
	}

	public void setResultats(List<BiArticles> resultats) {
		this.resultats = resultats;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void rechercher() {
		this.setResultats(articleUtil.getListeLivres(recherche));
		if (resultats.isEmpty()) {
			this.setMessage("Aucun article trouvé");
		} else {
			this.setMessage("");
		}
	}
}