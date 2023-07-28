package com.mmsystem.property.repo;


import java.util.List;


import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.query.Query;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Repository;
import com.mmsystem.property.model.MmsTenant;

import jakarta.transaction.Transactional;
  


@Repository 
public class MmsTenantRepository  implements IMmspRepository<MmsTenant> {  

  @Autowired  
  private SessionFactory sessionFactory;  
  
  @Transactional
  @Override  
  public MmsTenant save(MmsTenant mmsTenant) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().persist(mmsTenant);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status == true ? mmsTenant : null;  
  }  

  @Override  
  public List<MmsTenant> get() {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsTenant> query = currentSession.createQuery("from MmsTenant", MmsTenant.class);
		  List<MmsTenant> list = query.getResultList(); 
		  return list;
		     	
  }  

  @Transactional
  @Override  
  public boolean delete(MmsTenant mmsTenant) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().remove(mmsTenant);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  }  

  @Override  
  public MmsTenant getByID(MmsTenant mmsTenant) {  
		
		  Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsTenant> query = currentSession
				  				.createQuery("from MmsTenant where tenantId=:tenantId",MmsTenant.class); 
		  query.setParameter("tenantId", mmsTenant.getTenantId());
		  List<MmsTenant> list=query.getResultList(); 
		  return list.get(0);
		
  } 
  
  @Transactional
  @Override  
  public boolean update(MmsTenant mmsTenant) {  
      boolean status=false;  
      try {  
          sessionFactory.getCurrentSession().merge(mmsTenant);  
          status=true;  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return status;  
  }  
  
    

}
