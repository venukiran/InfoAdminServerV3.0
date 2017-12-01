package com.slt.infobus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.slt.infobus.entity.IBScreenDtl;
import com.slt.infobus.entity.IBScreenPositionDtl;

@Repository
@Transactional
public class ScreenPositionDAO {
	@PersistenceContext
	EntityManager entityManager;
	
	public List findAll(){
		return entityManager.createQuery("from IBScreenPositionDtl").getResultList();
	}
	public IBScreenPositionDtl save(IBScreenPositionDtl screenpos){
		return entityManager.merge(screenpos);
	}

	public IBScreenPositionDtl getScreen(int id){
		String qry ="from IBScreenDtl  where scrnPosId = "+id;
		return (IBScreenPositionDtl)entityManager.createQuery(qry).getSingleResult();
	}
	
	public void delete(IBScreenPositionDtl msg){
		entityManager.remove(msg);
	}
		
}
