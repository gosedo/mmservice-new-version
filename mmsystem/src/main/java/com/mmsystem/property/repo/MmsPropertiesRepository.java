package com.mmsystem.property.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmsystem.property.model.MmsProperty;
import jakarta.transaction.Transactional;


@Repository
public class MmsPropertiesRepository  implements IMmspRepository<MmsProperty> {
	
	@Autowired  
	private SessionFactory sessionFactory; 

	@Transactional
	@Override  
	public MmsProperty save(MmsProperty model) {
		boolean status=false;  
	      try {  
	          sessionFactory.getCurrentSession().persist(model);  
	          status=true;  
	      } catch (Exception e) {  
	          e.printStackTrace();  
	      }  
	      return status == true ? model : null; 
	}

	@Override
	public List<MmsProperty> get() {
		Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsProperty> query = currentSession.createQuery("from MmsProperty", MmsProperty.class);
		  List<MmsProperty> list = query.getResultList(); 
		  return list;
	}
	
	@Transactional
	@Override
	public boolean delete(MmsProperty model) {
		boolean status=false;  
	      try {  
	          sessionFactory.getCurrentSession().remove(model);  
	          status=true;  
	      } catch (Exception e) {  
	          e.printStackTrace();  
	      }  
	      return status; 
	}

	@Override
	public MmsProperty getByID(MmsProperty model) {
		Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsProperty> query = currentSession
				  				.createQuery("from MmsProperty where propertyId=:propertyId",MmsProperty.class); 
		  query.setParameter("propertyId", model.getPropertyId());
		  List<MmsProperty> list= query.getResultList(); 
		  return list.get(0);
	}
	
	@Transactional
	@Override
	public boolean update(MmsProperty model) {
		boolean status=false;  
	      try {  
	          sessionFactory.getCurrentSession().merge(model);  
	          status=true;  
	      } catch (Exception e) {  
	          e.printStackTrace();  
	      }  
	      return status; 
	}

}
