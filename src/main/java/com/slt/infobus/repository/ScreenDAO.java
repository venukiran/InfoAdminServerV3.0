package com.slt.infobus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.slt.infobus.entity.IBScreenDtl;

@Repository
@Transactional
public class ScreenDAO {
	@PersistenceContext
	EntityManager entityManager;
	
	public List findAll(){
		return entityManager.createQuery("from IBScreenDtl").getResultList();
	}
	
	public IBScreenDtl getScreen(int id){
		String qry ="from IBScreenDtl  where screenId = "+id;
		return (IBScreenDtl)entityManager.createQuery(qry).getSingleResult();
	}
	
	public void delete(IBScreenDtl msg){
		entityManager.remove(msg);
	}
		
	public IBScreenDtl save(IBScreenDtl screen){
		return entityManager.merge(screen);
	}
}
