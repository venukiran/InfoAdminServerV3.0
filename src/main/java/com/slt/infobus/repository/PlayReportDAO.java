package com.slt.infobus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.slt.infobus.entity.IBPlayReportDtl;

@Repository
@Transactional
public class PlayReportDAO {
	@PersistenceContext
	EntityManager entityManager;
	
	public List<IBPlayReportDtl> getAll(){
		return entityManager.createQuery("from IBPlayReportDtl").getResultList();
	}
	
	public IBPlayReportDtl getPlayReport(String id){
		return (IBPlayReportDtl) entityManager.createQuery("from IBPlayReportDtl where playId="+id).getSingleResult();
	}
	public IBPlayReportDtl save(IBPlayReportDtl loc){
		return entityManager.merge(loc);
	}
	
	public List<IBPlayReportDtl> getPlayReportData(String stDate, String enDate, String location){
		String qry = "FROM IBPlayReportDtl "
				+ "WHERE locName = '"+location+"' AND " 
				+ "playDate BETWEEN '"+stDate+"' AND '"+enDate+"'"
				+ "order by locName,videoName";
		return  entityManager.createQuery(qry).getResultList();
	}
}
