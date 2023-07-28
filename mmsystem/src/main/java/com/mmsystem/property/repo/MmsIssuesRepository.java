package com.mmsystem.property.repo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmsystem.property.controller.MmsIssueController;
import com.mmsystem.property.model.MmsMaintenanceIssue;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Still in migration to JPA but used with custom config.
 * It used SessionFactory bean for hibernate for database related operations. 
 * Used for issues 
 * */

@Slf4j 
@Repository
public class MmsIssuesRepository implements IMmspRepository<MmsMaintenanceIssue>{

	@Autowired  
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public MmsMaintenanceIssue save(MmsMaintenanceIssue model) {
		
		boolean status=false;  
	      try {  
	          sessionFactory.getCurrentSession().persist(model);  
	          status=true;  
	          
	      } catch (HibernateException e) { 
	    	  log.error("MmsIssuesRepository save" , e );
	          e.printStackTrace();  
	      }  
	      return status == true ? model : null;  
				
	}

	@Override
	public List<MmsMaintenanceIssue> get() {
		
		List<MmsMaintenanceIssue> list = new ArrayList<>();
		
		try {
				Session currentSession = sessionFactory.getCurrentSession(); 
				Query<MmsMaintenanceIssue> query = currentSession.createQuery("from MmsMaintenanceIssue", MmsMaintenanceIssue.class);
				list = query.getResultList(); 
			
		} catch (HibernateException e) { 
				log.error("MmsIssuesRepository get" , e );
				e.printStackTrace();  
	    } 
		  
		return list;
	}
	
	@Transactional
	@Override
	public boolean delete(MmsMaintenanceIssue model) {
		boolean status=false;  
	      try {  
	          sessionFactory.getCurrentSession().remove(model);  
	          status=true;  
	      } catch (HibernateException e) { 
	    	  log.error("MmsIssuesRepository delete" , e );
	          e.printStackTrace();  
	      }  
	      return status; 
		
	}

	@Override
	public MmsMaintenanceIssue getByID(MmsMaintenanceIssue model) {
		
		MmsMaintenanceIssue list = null ;
		
		try {
				Session currentSession = sessionFactory.getCurrentSession(); 
				Query<MmsMaintenanceIssue> query = currentSession
					  				.createQuery("from MmsMaintenanceIssue where issueId=:issueId",MmsMaintenanceIssue.class); 
				query.setParameter("issueId", model.getIssueId());
				list = query.getSingleResult(); 
				
		} catch (HibernateException e) { 
			log.error("MmsIssuesRepository getByID" , e );
	          e.printStackTrace();  
		}  
		
		return list;
	}

	@Override
	public boolean update(MmsMaintenanceIssue model) {
		boolean status=false;  
	      try {  
	          sessionFactory.getCurrentSession().merge(model);  
	          status=true;  
	      } catch (HibernateException e) { 
	    	  log.error("MmsIssuesRepository update" , e );
	          e.printStackTrace();  
	      }  
	      return status; 
	}

}
