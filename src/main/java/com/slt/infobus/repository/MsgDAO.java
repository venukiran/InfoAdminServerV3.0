package com.slt.infobus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.slt.infobus.entity.IBLocationDtl;
import com.slt.infobus.entity.IBMsgContentDtl;

@Repository
@Transactional
public class MsgDAO {
	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<IBMsgContentDtl> findAll(){
		return entityManager.createQuery("from IBMsgContentDtl").getResultList();
	}
	
	public IBMsgContentDtl getMsgContent(int id){
		String qry ="from IBMsgContentDtl  where msgId = "+id;
		return (IBMsgContentDtl)entityManager.createQuery(qry).getSingleResult();
	}
	
	public void delete(IBMsgContentDtl msg){
		entityManager.remove(msg);
	}
	
	@SuppressWarnings("unchecked")
	public List<IBMsgContentDtl> findAllActive(String loc){
		return entityManager.createQuery("from IBMsgContentDtl m where m.status='Active' and m.location.locationId="+loc).getResultList();
	}
	
	public IBMsgContentDtl save(IBMsgContentDtl loc){
		return entityManager.merge(loc);
	}
}
