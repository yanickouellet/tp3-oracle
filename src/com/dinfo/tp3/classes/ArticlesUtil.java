package com.dinfo.tp3.classes;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ArticlesUtil {
	
	Session session = null;
    
    public ArticlesUtil()
    {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public List<BiArticles> GetArticlesParMembre(int noMembre)
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
