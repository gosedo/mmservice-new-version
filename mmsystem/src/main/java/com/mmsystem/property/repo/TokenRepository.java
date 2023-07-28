package com.mmsystem.property.repo;


import java.util.List;  


import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.query.Query;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mmsystem.property.model.MmsToken;

import jakarta.transaction.Transactional;
  


@Repository 
public class TokenRepository  implements IMmspRepository<MmsToken> {  

    
  private final SessionFactory sessionFactory;  
  
  @Autowired
  public TokenRepository(@Qualifier("secondSessionFactory") SessionFactory sessionFactory) {
	  this.sessionFactory = sessionFactory;
  }
  
  @Transactional
  @Override  
  public MmsToken save(MmsToken mmsToken) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().persist(mmsToken);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status == true ? mmsToken : null;  
  }  

  @Override  
  public List<MmsToken> get() {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsToken> query = currentSession.createQuery("from MmsToken", MmsToken.class);
		  List<MmsToken> list = query.getResultList(); 
		  return list;
		     	
  }  

  @Transactional
  @Override  
  public boolean delete(MmsToken mmsToken) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().remove(mmsToken);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  }  

  @Override  
  public MmsToken getByID(MmsToken mmsToken) {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsToken> query = currentSession
				  				.createQuery("from MmsToken where mmsTokenId=:mmsTokenId",MmsToken.class); 
		  query.setParameter("mmsTokenId", mmsToken.getId());
		  List<MmsToken> list=query.getResultList(); 
		  return list.get(0);
		
  }  
  @Transactional
  @Override  
  public boolean update(MmsToken mmsToken) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().merge(mmsToken);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  } 
  
  public MmsToken getByAuthTokenStr(String authTokenStr) {  
		
	  Session currentSession = sessionFactory.getCurrentSession(); 
	  Query<MmsToken> query = currentSession
			  				.createQuery("from MmsToken where authToken=:authToken",MmsToken.class); 
	  query.setParameter("authToken", authTokenStr);
	  MmsToken mmsToken=query.getSingleResult(); 
	  return mmsToken;
	
  }
    
    

}
