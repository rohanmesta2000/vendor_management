package com.xworkz.vendorManagement.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.vendorManagement.entity.VendorEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j

public class VendorRepositoryImpl implements VendorRepository {

	@Autowired
	private EntityManagerFactory emf;
	@Override
	public boolean save(VendorEntity entity) {
		log.info("Invoking save...");
		log.info("Entity Passed:" + entity);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			log.info("Et begin");
			em.persist(entity);
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
	public List<VendorEntity> findAll() {
		EntityManager em = emf.createEntityManager();
		log.info("Created EM");
		List<VendorEntity> list = new ArrayList<VendorEntity>();
		try {
			Query query = em.createNamedQuery("findAll");
			list = query.getResultList();

		} catch (PersistenceException pe) {
			log.info("PersistenceException in save:" + pe.getMessage());

		} finally {
			log.info("Closing resources");
			em.close();
			log.info("Em closed");
		}
		return list;
	}

	@Override
	public VendorEntity isExistGstNoContactNoEmailWebsite(String gstNo, Long contactNo, String email, String website) {
		EntityManager em = emf.createEntityManager();
		log.info("Created EM");
		VendorEntity entity = null;
		try {
			Query query = em.createNamedQuery("isExistGstNoContactNoEmailWebsite");
			query.setParameter("gn", gstNo);
			query.setParameter("cn", contactNo);
			query.setParameter("vm", email);
			query.setParameter("web", website);
			entity = (VendorEntity) query.getSingleResult();

		} catch (PersistenceException pe) {
			log.info("PersistenceException in save:" + pe.getMessage());

		} finally {
			log.info("Closing resources");
			em.close();
			log.info("Em closed");
		}
		return entity;
	}

	@Override
	public boolean findByEmail(String email) {
		EntityManager entityManager = emf.createEntityManager();
		System.err.println("findByEmail repo");
		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("countByEmail");
			query.setParameter("email", email);
			Long count = (Long) query.getSingleResult();
			entityManager.getTransaction().commit();
			return count > 0;
		} catch (PersistenceException e) {
			e.printStackTrace();
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return false;
	}

	@Override
	public boolean saveLoginOtpByemaild(String email, String otp, LocalDateTime generateOtpTime) {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		System.out.println("connection is ok");
		try {
			entityTransaction.begin();
			Query query = entityManager.createNamedQuery("saveLoginOtpByemaild");
			query.setParameter("otp", otp);
			query.setParameter("generateOtpTime", generateOtpTime);
			query.setParameter("email", email);

			int rowsUpdated = query.executeUpdate();
			entityTransaction.commit();
			if (rowsUpdated == 0) {
				System.out.println("OTP not updated successfully");
				return false;
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
				return false;
			}
		} finally {
			entityManager.close();
		}
		return true;
	}

	@Override
	public Object[] getloginOTPAndgenratedTime(String email) {
		System.err.println("findLatestOtpByEmail=======================");
		EntityManager entityManager = emf.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("getloginOTPAndgenratedTime");
			query.setParameter("email", email);
			return (Object[]) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String imagePathByEmail(String email) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			TypedQuery<String> query = entityManager.createNamedQuery("findImagePathByEmail", String.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException e) {

			return null;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

	@Override
	public boolean updateVendorDetailsById(VendorEntity entity, int id) {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			VendorEntity entity2 = entityManager.find(VendorEntity.class, id);
			if (entity2 != null) {
				entity2.setOwnerName(entity.getOwnerName());
				entity2.setEmail(entity.getEmail());
				entity2.setContactNo(entity.getContactNo());
				entity2.setAlternativeNo(entity.getAlternativeNo());
				entity2.setVendorName(entity.getVendorName());
				entity2.setGstNo(entity.getGstNo());
				entity2.setCompanyStartDate(entity.getCompanyStartDate());
				entity2.setWebsite(entity.getWebsite());
				entity2.setAddress(entity.getAddress());
				entity2.setPincode(entity.getPincode());
				entity2.setImagePath(entity.getImagePath());
				entityManager.merge(entity2);
				entityTransaction.commit();
				System.out.println("Data updated successfully");
				return true;
			} else {
				return false;
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			return false;
		} finally {
			entityManager.close();
		}
	}
	
	@Override
	public VendorEntity findAllVendorEntityByEmail(String email) {
		log.info("Invoking findAllVendorEntityByEmail....");
		EntityManager em = emf.createEntityManager();
		log.info("Created EM");
		VendorEntity entity = null;
		try {
			Query query = em.createNamedQuery("findAllVendorEntityByEmail");
			query.setParameter("email", email);

			entity = (VendorEntity) query.getSingleResult();

		} catch (PersistenceException pe) {
			log.info("PersistenceException in save:" + pe.getMessage());

		} finally {
			log.info("Closing resources");
			em.close();
			log.info("Em closed");
		}
		return entity;
	}

	@Override
	public int getVendorIdByEmail(String email) {
		EntityManager entityManager = emf.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("getVendorIdByEmail");
			query.setParameter("email", email);
			int  count = (int) query.getFirstResult();
			entityManager.getTransaction().commit();
			return count ;
		} catch (PersistenceException e) {
			e.printStackTrace();
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return 0;
	}
}
