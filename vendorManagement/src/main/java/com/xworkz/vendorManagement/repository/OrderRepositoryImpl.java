package com.xworkz.vendorManagement.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.vendorManagement.entity.OrderEntity;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class OrderRepositoryImpl  implements OrderRepository{
	
	@Autowired
	private EntityManagerFactory emf;

	@Override
	public boolean save(OrderEntity orderEntity) {
		
		log.info("Invoking save.....");
		log.info("OrderEntity Passed:" +orderEntity);
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		try {
			et.begin();
			log.info("Et begin");
			em.persist(orderEntity);
			log.info("Persist complete");
			et.commit();
		} catch (PersistenceException pe) {
			log.info("PersistenceException in save:" + pe.getMessage());
			et.rollback();
		} finally {
			log.info("Closing resources");
			em.close();
		}
		return true;
	}
	

	@Override
	public List<OrderEntity> viewAllOrders(int vendorId) {
    EntityManager em=emf.createEntityManager();
		try {
			Query query=em.createNamedQuery("viewAllOrdersByVendorId");
			query.setParameter("vendorId", vendorId);
			List<OrderEntity> read=query.getResultList();
			return read;
			} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		return null;
	}

}
