package com.slt.infobus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.slt.infobus.entity.IBLocationDtl;

@Repository
@Transactional
public class LocationDAO {
	@PersistenceContext
	EntityManager entityManager;
	
	public List findAll(){
		return entityManager.createQuery("from IBLocationDtl").getResultList();
	}
		
	public IBLocationDtl getLocation(int id){
		String qry ="from IBLocationDtl loc where locationId = "+id;
		return (IBLocationDtl)entityManager.createQuery(qry).getSingleResult();
	}
	
	public void delete(IBLocationDtl loc){
		entityManager.remove(loc);
	}
	
	public IBLocationDtl save(IBLocationDtl loc){		
		return entityManager.merge(loc);
	}
	
	
}
