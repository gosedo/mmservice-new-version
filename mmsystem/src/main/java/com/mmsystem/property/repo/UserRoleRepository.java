package com.mmsystem.property.repo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.query.Query;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Repository;

import com.mmsystem.property.model.MmsUser;
import com.mmsystem.property.model.MmsUserRole;

import jakarta.transaction.Transactional;
  


@Repository 
public class UserRoleRepository  implements IMmspRepository<MmsUserRole> {  

  @Autowired  
  private SessionFactory sessionFactory;  
  
  @Transactional
  @Override  
  public MmsUserRole save(MmsUserRole mmsUserRole) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().persist(mmsUserRole);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status == true ? mmsUserRole : null;  
  }  

  @Override  
  public List<MmsUserRole> get() {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsUserRole> query = currentSession.createQuery("from MmsUserRole", MmsUserRole.class);
		  List<MmsUserRole> list = query.getResultList(); 
		  return list;
		     	
  }  

  @Transactional
  @Override  
  public boolean delete(MmsUserRole mmsUserRole) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().remove(mmsUserRole);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  }  

  @Override  
  public MmsUserRole getByID(MmsUserRole mmsUserRole) {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsUserRole> query = currentSession
				  				.createQuery("from User where userId=:userId",MmsUserRole.class); 
		  query.setParameter("userId", mmsUserRole.getUsrRoleId());
		  List<MmsUserRole> list=query.getResultList(); 
		  return list.get(0);
		
  } 
  
  @Transactional
  @Override  
  public boolean update(MmsUserRole mmsUserRole) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().merge(mmsUserRole);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  }  
  
  //Additional Methods
  public List<MmsUserRole> getRolesByUserId(int userId) {  
		
	  Session currentSession = sessionFactory.getCurrentSession(); 
	  Query<MmsUser> query = currentSession
			  				.createQuery("from MmsUser where userId=:userId",MmsUser.class); 
	  query.setParameter("userId", userId);
	  MmsUser user  = query.getSingleResult();
	  
	  List<MmsUserRole> roleList = new ArrayList<MmsUserRole>();
	  roleList.addAll(user.getUserRoles());
	  
	  return roleList;
	
  }
  
    
    

}
