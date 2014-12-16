package com.dinfo.tp3.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.dinfo.tp3.classes.ArticleUtil;
import com.dinfo.tp3.classes.BiArticles;

@ManagedBean
@RequestScoped
public class ConsultationBean {
	private String erreur, isbn;
	private BiArticles article;
	private ArticleUtil articleUtil;
	

	public ConsultationBean() {
		articleUtil = new ArticleUtil();
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