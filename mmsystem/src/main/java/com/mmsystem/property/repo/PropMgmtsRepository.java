package com.mmsystem.property.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmsystem.property.model.MmsPropertyManagement;
import com.mmsystem.property.model.MmsUser;

import jakarta.transaction.Transactional;


@Repository
public class PropMgmtsRepository implements IMmspRepository<MmsPropertyManagement>{

	@Autowired  
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public MmsPropertyManagement save(MmsPropertyManagement model) {
		
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
	public List<MmsPropertyManagement> get() {
		
		Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsPropertyManagement> query = currentSession.createQuery("from MmsPropertyManagement", MmsPropertyManagement.class);
		  List<MmsPropertyManagement> list = query.getResultList(); 
		  return list;
	}
	
	@Transactional
	@Override
	public boolean delete(MmsPropertyManagement model) {
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
	public MmsPropertyManagement getByID(MmsPropertyManagement model) {
		Session currentSession = sessionFactory.getCurrentSession(); 
		  Query<MmsPropertyManagement> query = currentSession
				  				.createQuery("from MmsPropertyManagement where pMgmtId=:pMgmtId",MmsPropertyManagement.class); 
		  query.setParameter("pMgmtId", model.getPMgmtId());
		  List<MmsPropertyManagement> list=query.getResultList(); 
		  return list.get(0);
	}

	@Override
	public boolean update(MmsPropertyManagement model) {
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
