package com.dinfo.tp3.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.dinfo.tp3.beans.ReservationBean;
import com.dinfo.tp3.classes.BiArticles;

public class ArticleUtil {

	Session session = null;

	public ArticleUtil() {
		// this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		this.session = HibernateUtil.getSessionFactory().openSession();
	}

	public List<BiArticles> getListeLivres(String recherche) {
		List<BiArticles> listeLivres = null;
		String param = "%" + recherche + "%";
		try {
			// Liste de tous les livres répondant au critère - Criteria
			Criteria criteria = session.createCriteria(BiArticles.class,
					"article");
			criteria.createAlias("biAuteurses", "auteurs",
					JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.or(Restrictions.or(
					Restrictions.like("auteurs.prenom", param),
					Restrictions.like("auteurs.nom", param)), Restrictions
					.like("article.titre", param)));
			listeLivres = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listeLivres;
	}

	public BiArticles getLivreWithISBN(String isbn) {
		BiArticles article = null;
		try {
			List<BiArticles> articles = session
					.createCriteria(BiArticles.class)
					.add(Restrictions.eq("isbn", isbn)).list();

			if (articles.size() > 0)
				article = articles.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public BiCopiesarticles getCopieArticleParIsbn(String isbn)
	{
		BiCopiesarticles copie = null;
        try {
        	List<BiCopiesarticles> liste = session
        			.createQuery("from BiCopiesarticles where isbn = :isbn and indicateurDisponible = 1 ")
        			.setString("no", isbn)
        			.list();
        	if(liste.size() > 0)
        		copie = liste.get(0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return copie;
	}
	
	public void ajouterReservation(BiMembres membre, BiArticles article) {
        Transaction tx = null;
        
        try{    
            tx = session.beginTransaction();
            BiReservation reservation = new BiReservation();
            reservation.setBiArticles(article);
            reservation.setBiMembres(membre);
            reservation.setDateReservation(new Date());
            
            session.save(reservation);
            tx.commit();
        }
        catch(Exception e)
        {
            tx.rollback();
            e.printStackTrace();
        }
	}
	
	public List<BiReservation> getReservationsParMembre(int noMembre)
    {
    	List<BiReservation> reservations = null;
        try {
        	reservations = session
        			.createQuery("from BiReservation where biMembres.noMembre = :no ")
        			.setInteger("no", noMembre)
        			.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reservations;
    }

    public List<BiArticles> getArticlesParMembre(int noMembre)
    {
    	List<BiArticles> articles = null;
        try {
        	articles = session
        			.createQuery("from BiEmprunts where biMembres.noMembre = :no ")
        			.setInteger("no", noMembre)
        			.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return articles;
    }
}