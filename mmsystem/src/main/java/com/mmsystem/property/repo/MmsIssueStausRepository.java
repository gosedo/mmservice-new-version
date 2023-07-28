package com.mmsystem.property.repo;

import java.util.List;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.query.Query;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Repository;

import com.mmsystem.property.model.MmsIssueStatus;


import jakarta.transaction.Transactional;
  
/**
 * Still in migration to JPA but used with custom config.
 * It used SessionFactory bean for hibernate for database related operations. 
 * Used for issues statuse
 * */

@Repository 
public class MmsIssueStausRepository  implements IMmspRepository<MmsIssueStatus> {  

  @Autowired  
  private SessionFactory sessionFactory;  
  
  @Transactional
  @Override  
  public MmsIssueStatus save(MmsIssueStatus mmsIssueStatus) {  
      
	  boolean status=false;  
	  
      try {  
          sessionFactory.getCurrentSession().persist(mmsIssueStatus);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status == true ? mmsIssueStatus : null;  
  }  

  @Override  
  public List<MmsIssueStatus> get() {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsIssueStatus> query = currentSession.createQuery("from MmsIssueStatus", MmsIssueStatus.class);
		  List<MmsIssueStatus> list = query.getResultList(); 
		  return list;
		     	
  }  

  @Transactional
  @Override  
  public boolean delete(MmsIssueStatus mmsIssueStatus) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().remove(mmsIssueStatus);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  }  

  @Override  
  public MmsIssueStatus getByID(MmsIssueStatus mmsIssueStatus) {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsIssueStatus> query = currentSession
				  				.createQuery("from MmsIssueStatus where issueStatusId=:issueStatusId",MmsIssueStatus.class); 
		  query.setParameter("issueStatusId", mmsIssueStatus.getIssueStatusId());
		  List<MmsIssueStatus> list=query.getResultList(); 
		  return list.get(0);
		
  } 
  
  @Transactional
  @Override  
  public boolean update(MmsIssueStatus mmsIssueStatus) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().merge(mmsIssueStatus);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  }  
  
    

}
