package com.slt.infobus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.slt.infobus.entity.IBServerDtl;
import com.slt.infobus.entity.IBVideoDtl;

@Repository
@Transactional
public class ServerDAO {
	@PersistenceContext
	EntityManager entityManager;
	
	public List<IBServerDtl> findAll(){
		return entityManager.createQuery("from IBServerDtl").getResultList();
	}
	
	public List<IBServerDtl> findByLocation(String loc){
		return entityManager.createQuery("from IBServerDtl s where s.locationId="+loc).getResultList();
	}
	
	public IBServerDtl save(IBServerDtl server){
		return entityManager.merge(server);
	}
	
	public IBServerDtl getServer(int id){
		String qry ="from IBServerDtl  where serverId = "+id;
		return (IBServerDtl)entityManager.createQuery(qry).getSingleResult();
	}
	
	public void delete(IBServerDtl server){
		entityManager.remove(server);
	}
}
