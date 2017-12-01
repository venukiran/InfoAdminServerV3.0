package com.slt.infobus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.slt.infobus.entity.IBSponserDtl;
import com.slt.infobus.entity.IBVideoDtl;

@Repository
@Transactional
public class VideoDAO {
	@PersistenceContext
	EntityManager entityManager;
	
	public List findAll(){
		return entityManager.createQuery("from IBVideoDtl").getResultList();
	}
	
	public IBVideoDtl getVideo(int id){
		String qry ="from IBVideoDtl  where videoId = "+id;
		return (IBVideoDtl)entityManager.createQuery(qry).getSingleResult();
	}
	
	public void delete(IBVideoDtl video){
		entityManager.remove(video);
	}
	
	public IBVideoDtl save(IBVideoDtl server){
		return entityManager.merge(server);
	}
}
