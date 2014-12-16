package com.dinfo.tp3.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.dinfo.tp3.classes.ArticleUtil;
import com.dinfo.tp3.classes.BiArticles;
import com.dinfo.tp3.classes.BiAuteurs;

@ManagedBean
@RequestScoped
public class ConsultationBean {
	private String erreur, isbn;
	private BiArticles article;
	private ArticleUtil articleUtil;
	private List<BiAuteurs> auteurs;
	

	public ConsultationBean() {
		articleUtil = new ArticleUtil();
	}

	public List<BiAuteurs> getAuteurs() {
		return auteurs;
	}

	public void setAuteurs(List<BiAuteurs> auteurs) {
		this.auteurs = auteurs;
	}
	
	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public BiArticles getArticle() {
		return article;
	}

	public void setArticle(BiArticles article) {
		this.article = article;
		List<BiAuteurs> auteurs = new ArrayList<BiAuteurs>();
		for(Object auteur : article.getBiAuteurses()) {
			auteurs.add((BiAuteurs) auteur);
		}
		setAuteurs(auteurs);
	}

	public ArticleUtil getArticleUtil() {
		return articleUtil;
	}

	public void setArticleUtil(ArticleUtil articleUtil) {
		this.articleUtil = articleUtil;
	}
	public String getIsbn() {
		return this.isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;

		this.setArticle(articleUtil.getLivreWithISBN(isbn));
		if (article == null) {
			this.setErreur("isbn invalide");
		}
	}
}