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
public class ReservationBean {
	private String isbn;
	private List<BiReservation> reservations;
	private ArticleUtil articleUtil;
	@ManagedProperty(value="#{connexionBean}")
	private ConnexionBean connexionBean;
	
	@javax.annotation.PostConstruct
	public void PostConstruct() { 
		ArticleUtil util = new ArticleUtil(); 
		setReservations(util.getReservationsParMembre(connexionBean.getNo()));
	}

	public void setConnexionBean(ConnexionBean connexionBean) {
		this.connexionBean = connexionBean;
	}


	public ConnexionBean getConnexionBean() {
		return connexionBean;
	}

	public ReservationBean() {
		articleUtil = new ArticleUtil();
	}
	

	public List<BiReservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<BiReservation> reservations) {
		this.reservations = reservations;
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
		
		if(connexionBean == null || !connexionBean.estConnecte()) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vous devez être connecté.", null));
			return;
		}
		
		if (isbn == null || isbn == "") {
			return;
		}
		
		BiArticles article = articleUtil.getLivreWithISBN(isbn);
		BiMembres membre = (new MembreUtil()).getMembre(connexionBean.getNo());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, article.getAgeMinimum() * -1);
		
		if (cal.getTime().compareTo(membre.getDateNaissance()) < 0) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vous n'avez pas l'âge requis.", null));
			return;
		}
		
		articleUtil.ajouterReservation(membre, article);
	}
	
	public boolean isCopieDisponible(BiReservation reservation) {
		for(Object object : reservation.getBiArticles().getBiCopiesarticleses()) {
			BiCopiesarticles copie = (BiCopiesarticles)object;
			if(copie.getIndicateurDisponible() == "1") {
				return true;
			}
		}
		return false;
	}
}