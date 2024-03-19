package com.xworkz.vManagement.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.vManagement.dto.VendorEntity;

@Repository
public class VendorRepoImpl implements VendorRepo {
	@Autowired
	private EntityManagerFactory emf;

	@Override
	public boolean save(VendorEntity entity) {
		System.out.println("calling save method from vManager to vManagerRepoImpl");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(entity);
			System.out.println("entity data is saved");
			et.commit();
		} catch (PersistenceException pe) {
			System.out.println("getting exception" + pe.getMessage());
			et.rollback();
		} finally {
			System.out.println("closing em...");
			em.close();
		}

		return true;
	}

	@Override
	public VendorEntity isExist(String gstNo, Long contactNo, String email, String website) {
		System.out.println("invoking is exist");

		EntityManager em = emf.createEntityManager();
		VendorEntity entity = null;

		try {

			Query query = em.createNamedQuery("isExistContactGstEmailWebsite");

			query.setParameter("gn", gstNo);
			query.setParameter("cn", contactNo);
			query.setParameter("em", email);
			query.setParameter("we", website);
			entity = (VendorEntity) query.getSingleResult();
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in save:" + pe.getMessage());
			return null;
		} finally {
			System.out.println("Closing resources");
			em.close();
			System.out.println("closed em");
		}
		return entity;
	}

	@Override
	public List<VendorEntity> FindAllByAjax() {
		EntityManager entityManager = emf.createEntityManager();
		System.out.println("Created EM");
		List<VendorEntity> list=new ArrayList<VendorEntity>();
		try {
			Query query = entityManager.createNamedQuery("FindAllByAjax");
			list = query.getResultList();

		} catch (PersistenceException e) {
			System.out.println("PersistenceException ruuning in FindAllByAjax" +e.getMessage());
		} finally {
			entityManager.close();
		}
					
		return list;
	}

	@Override
	public VendorEntity isExistByEmailOtp(String email, String otp) {
		System.out.println("invoking in is isExistByEmailOtp");
		EntityManager entityManager = emf.createEntityManager();
		System.out.println("EntityManger:" + entityManager);
		VendorEntity entity = null;
		try {
			Query query = entityManager.createNamedQuery("isExistByEmailOtp");
			query.setParameter("email", email);
			query.setParameter("otp", otp);
			entity = (VendorEntity) query.getSingleResult();

		} catch (PersistenceException pe) {
			System.out.println("Persisitence is saved" + pe.getMessage());
		} finally {
			entityManager.close();
		}
		return entity;
	}

	@Override
	public void updatedOtpByEmail(String email, String otp) {
		System.out.println("invoking in updatedOtpByEmail");
		EntityManager entityManager = this.emf.createEntityManager();
		System.out.println("EntityManager:" + entityManager);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			Query query = entityManager.createNamedQuery("updatedOtpByEmail");
			query.setParameter("email", email);
			Object object = query.getSingleResult();
			VendorEntity entity = (VendorEntity) object;
			if (entity != null) {
				entity.setOtp(otp);
				entity.setOtpGeneratedTime(LocalDateTime.now());
				entityManager.merge(entity);
				entityTransaction.commit();
				System.out.println("updatedOtpByEmail is updated");
			}
		} catch (PersistenceException pe) {
			System.out.println("Persistance is saved :" + pe.getMessage());

		} finally {
			entityManager.close();

		}

	}

	@Override
	public VendorEntity findByEmail(String email){
    EntityManager em=emf.createEntityManager();
    System.out.println("Created EM");
     VendorEntity list=new VendorEntity();
     try {
    	Query query=em.createNamedQuery("findByEmail");
    	query.setParameter("email", email);
    	list=(VendorEntity) query.getSingleResult();
		
	} catch (PersistenceException pe) {
System.out.println("Persistance in save:" +pe.getMessage() );

	}finally {
		System.out.println("closing resources");
		em.close();
		System.out.println("Em Closed");
	}
		
		return list;
	}

}
