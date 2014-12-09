package com.dinfo.tp3.classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    
    public MembreUtil()
    {
        //this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public BiMembres GetMembreAuthentifie(String login, String motPasse)
    {
        BiMembres membre = null;
    	
        try {
        	List<BiMembres> membres = session.createQuery("from BiMembres").list();
            
        	if (membres.size() > 0)
        		membre = membres.get(0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return membre;
    }
    

}
