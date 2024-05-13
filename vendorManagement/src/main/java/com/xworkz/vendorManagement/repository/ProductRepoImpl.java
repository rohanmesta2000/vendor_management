package com.xworkz.vendorManagement.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.vendorManagement.entity.ProductEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ProductRepoImpl implements ProductRepository {
	@Autowired
	private EntityManagerFactory emf;

	@Override
	public boolean save(ProductEntity entity) {
		log.info("Invoking save...");
		log.info("AddProductEntity Passed:" + entity);
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
	public List<ProductEntity> readAll() {
		EntityManager em = emf.createEntityManager();
		log.info("Created EM");
		List<ProductEntity> list = new ArrayList<ProductEntity>();
		try {
			Query query = em.createNamedQuery("readAll");
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
	public List<ProductEntity> getAllProductDetailsByVendorId(int vendorId) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("getAllProductDetailesByVendotId");
			query.setParameter("vendorId", vendorId);
			List<ProductEntity> read = query.getResultList();
			return read;
		} catch (Exception e) {
		} finally {

		}
		return null;
	}

	@Override
	public ProductEntity getAllProductsById(int id) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("getAllProductsById");
			query.setParameter("id", id);
			ProductEntity read = (ProductEntity) query.getSingleResult();
			return read;
		} catch (Exception e) {
		} finally {

		}
		return null;
	}

	@Override
	public boolean updateProductDetailsById(ProductEntity productEntity, int id) {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			ProductEntity entity2 = entityManager.find(ProductEntity.class, id);
			if (entity2 != null) {
				entity2.setCategory(productEntity.getCategory());
				entity2.setProductName(productEntity.getProductName());
				entity2.setProductPrice(productEntity.getProductPrice());
				entity2.setDeliveryCharge(productEntity.getDeliveryCharge());
				entity2.setDescription(productEntity.getDescription());
				entity2.setAvailable(productEntity.getAvailable());
				entity2.setVendor(productEntity.getVendor());

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

}

