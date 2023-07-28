package com.mmsystem.property.repo;

import java.util.List;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.query.Query;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Repository;
import com.mmsystem.property.model.MmsUser;
import jakarta.transaction.Transactional;
 
@Repository 
public class UserRepository  implements IMmspRepository<MmsUser> {  

  @Autowired  
  private SessionFactory sessionFactory; 
  
  
  @Transactional
  @Override  
  public MmsUser save(MmsUser mmsuser) {  
      boolean status=false; 
      Session session = sessionFactory.getCurrentSession();
      
      try {  

    	  session.persist(mmsuser); 
    	   
          status=true;  
          
      } catch (Exception e) {
    	  
    	  e.printStackTrace();  
    	  
      } 
      return status == true ? mmsuser : null;  
  }  

  @Override  
  public List<MmsUser> get() {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsUser> query = currentSession.createQuery("from MmsUser", MmsUser.class);
		  List<MmsUser> list = query.getResultList(); 
		  return list;
		     	
  }  

  @Transactional
  @Override  
  public boolean delete(MmsUser mmsUser) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().remove(mmsUser);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  }  

  @Override  
  public MmsUser getByID(MmsUser mmsUser) {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsUser> query = currentSession
				  				.createQuery("from MmsUser where userId=:userId",MmsUser.class); 
		  query.setParameter("userId", mmsUser.getUserId());
		  List<MmsUser> list=query.getResultList(); 
		  return list.get(0);
		
  } 
  
  @Transactional
  @Override  
  public boolean update(MmsUser mmsUser) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().merge(mmsUser);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  }  
  
  //Additional Methods
  public MmsUser getByID(int userId) {  
		
	  Session currentSession = sessionFactory.getCurrentSession(); 
	  Query<MmsUser> query = currentSession
			  				.createQuery("from MmsUser where userId=:userId",MmsUser.class); 
	  query.setParameter("userId", userId);
	  List<MmsUser> list=query.getResultList(); 
	  return list.get(0);
	
  }
  
  public MmsUser getByEmail(String userEmail) {  
		
	  Session currentSession = sessionFactory.getCurrentSession(); 
	  Query<MmsUser> query = currentSession
			  				.createQuery("from MmsUser where userEmail=:userEmail",MmsUser.class); 
	  query.setParameter("userEmail", userEmail);
	  List<MmsUser> list=query.getResultList(); 
	  return list.get(0);
	
  }
  
  public MmsUser getByCredential(String userEmail, String userPassword) {  
		
	  Session currentSession = sessionFactory.getCurrentSession(); 
	  Query<MmsUser> query = currentSession
			  				.createQuery("from MmsUser where userEmail=:userEmail and userPassword=: userPassword",MmsUser.class); 
	  query.setParameter("userEmail", userEmail);
	  query.setParameter("userPassword", userPassword);
	  MmsUser userWithCredential = query.getSingleResult(); 
	  return userWithCredential;
	
  }
    
    

}
