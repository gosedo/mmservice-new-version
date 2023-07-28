package com.mmsystem.property.repo;


import java.util.List;


import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.query.Query;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Repository;

import com.mmsystem.property.model.MmsIssueType;



import jakarta.transaction.Transactional;
  


@Repository 
public class MmsIssueTypeRepository  implements IMmspRepository<MmsIssueType> {  

  @Autowired  
  private SessionFactory sessionFactory;  
  
  @Transactional
  @Override  
  public MmsIssueType save(MmsIssueType mmsIssueType) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().persist(mmsIssueType);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status == true ? mmsIssueType : null;  
  }  

  @Override  
  public List<MmsIssueType> get() {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsIssueType> query = currentSession.createQuery("from MmsIssueType", MmsIssueType.class);
		  List<MmsIssueType> list = query.getResultList(); 
		  return list;
		     	
  }  

  @Transactional
  @Override  
  public boolean delete(MmsIssueType mmsIssueType) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().remove(mmsIssueType);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  }  

  @Override  
  public MmsIssueType getByID(MmsIssueType mmsIssueType) {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsIssueType> query = currentSession
				  				.createQuery("from MmsIssueType where issueTypeId=:issueTypeId",MmsIssueType.class); 
		  query.setParameter("issueTypeId", mmsIssueType.getIssueTypeId());
		  List<MmsIssueType> list=query.getResultList(); 
		  return list.get(0);
		
  } 
  
  @Transactional
  @Override  
  public boolean update(MmsIssueType mmsIssueType) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().merge(mmsIssueType);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  }  
  
    

}
