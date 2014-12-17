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

public class MembreUtil {
	
	Session session = null;
    
    public MembreUtil() {
		this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public MembreUtil(Session session) {
    	this.session = session;
    }
    
    public BiMembres getMembreAuthentifie(String login, String motPasse)
    {
        BiMembres membre = null;
        try {
        	List<BiMembres> membres = session
        			.createQuery("from BiMembres where login=:login "
        			+ "and motPasse=:motPasse")
        			.setString("login", login)
        			.setString("motPasse", sha256(motPasse))
        			.list();
            
        	if (membres.size() > 0)
        		membre = membres.get(0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return membre;
    }
    
    public BiMembres getMembreParLogin(String login)
    {
        BiMembres membre = null;;
        try {
        	List<BiMembres> membres = session
        			.createQuery("from BiMembres where login=:login ")
        			.setString("login", login)
        			.list();
            
        	if (membres.size() > 0)
        		membre = membres.get(0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return membre;
    }
    
    public void resetMotPasse(BiMembres membre, String motPasse) {
        Transaction tx = null;
        
        try{    
            tx = session.beginTransaction();
            membre.setMotPasse(sha256(motPasse));
            
            session.save(membre);
            tx.commit();
        }
        catch(Exception e)
        {
            tx.rollback();
            e.printStackTrace();
        }
	}
    
    public BiMembres getMembre(int no) {
    	try {
        	return (BiMembres) this.session.load(BiMembres.class, no);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
    }
    
    /**
     * Inspiré de https://gist.github.com/avilches/750151
     * @param valeur
     * @return
     */
    private String sha256(String valeur) {
    	if(valeur.equals(""))
    		return "";

    	MessageDigest md;
    	String hash = "";
		try {
			md = MessageDigest.getInstance("SHA-256");
            md.update(valeur.getBytes("UTF-8"));
            StringBuffer result = new StringBuffer();
            for (byte byt : md.digest()) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
            hash = result.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		} 

    	return hash;
    }
}
