package com.mmsystem.property.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.mmsystem.property.model.MmsUnit;

import jakarta.transaction.Transactional;


@Repository
public class MmsUnitsRepository  implements IMmspRepository<MmsUnit> {

	@Autowired  
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public MmsUnit save(MmsUnit model) {
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
	public List<MmsUnit> get() {
		Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsUnit> query = currentSession.createQuery("from MmsUnit", MmsUnit.class);
		  List<MmsUnit> list = query.getResultList(); 
		  return list;
	}

	@Transactional
	@Override
	public boolean delete(MmsUnit model) {
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
	public MmsUnit getByID(MmsUnit model) {
		Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsUnit> query = currentSession
				  				.createQuery("from MmsUnit where unitId=:unitId",MmsUnit.class); 
		  query.setParameter("unitId", model.getUnitId());
		  List<MmsUnit> list=query.getResultList(); 
		  return list.get(0);
	}

	@Override
	public boolean update(MmsUnit model) {
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
