package com.dinfo.tp3.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.dinfo.tp3.classes.ArticleUtil;
import com.dinfo.tp3.classes.BiArticles;
import com.dinfo.tp3.classes.BiAuteurs;
import com.dinfo.tp3.classes.BiCopiesarticles;
import com.dinfo.tp3.classes.BiMembres;
import com.dinfo.tp3.classes.BiReservation;
import com.dinfo.tp3.classes.MembreUtil;

@ManagedBean
@RequestScoped
public class LocationBean {
	private int noMembre;
	private String isbn;
	private ArticleUtil articleUtil;
	private MembreUtil membreUtil;
	@ManagedProperty(value="#{connexionBean}")
	private ConnexionBean connexionBean;
	
	@javax.annotation.PostConstruct
	public void PostConstruct() { 
	}

	public void setConnexionBean(ConnexionBean connexionBean) {
		this.connexionBean = connexionBean;
	}


	public ConnexionBean getConnexionBean() {
		return connexionBean;
	}

	public LocationBean() {
		articleUtil = new ArticleUtil();
		membreUtil = new MembreUtil(articleUtil.getSession());
	}
	
	public int getNoMembre() {
		return noMembre;
	}

	public void setNoMembre(int noMembre) {
		this.noMembre = noMembre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String enregistrer() {
		BiMembres membre = membreUtil.getMembre(noMembre);
		BiArticles article = articleUtil.getLivreWithISBN(isbn);
		
		if(membre == null) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le membre n'existe pas.", null));
			return "";
		}
		
		if(article == null) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "L'article n'existe pas.", null));
			return "";
		}
		
		BiCopiesarticles copie = getCopieDisponible(article);
		
		if(copie == null) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aucune copie disponible", null));
			return "";
		}
		
		if(copie.getBiArticles().getBiReservations().size() > 0 && !membreAReserver(membre, copie.getBiArticles())) {
			FacesContext.getCurrentInstance().addMessage("", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "L'article a été reservé par un autre membre.", null));
			return "";
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 14);
		
		articleUtil.emprunter(membre, copie, cal.getTime());
		
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Emprunt effectué", null));
		setNoMembre(0);
		setIsbn("");
		
		return "";
	}
	
	private boolean membreAReserver(BiMembres membre, BiArticles article) {
		for(Object objRes : article.getBiReservations()) {
			BiReservation res = (BiReservation)objRes;
			if(res.getBiMembres().getNoMembre() == membre.getNoMembre()) {
				return true;
			}
		}
		
		return false;
	}
	
	private BiCopiesarticles getCopieDisponible(BiArticles article) {
		for(Object objCopie : article.getBiCopiesarticleses()) {
			BiCopiesarticles copie = (BiCopiesarticles)objCopie;
			if(copie.getIndicateurDisponible().equals("1"))
				return copie;
		}
		return null;
	}
}