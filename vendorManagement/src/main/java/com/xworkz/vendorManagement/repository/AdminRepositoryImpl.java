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

import com.xworkz.vendorManagement.entity.AdminEntity;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
	@Autowired
	private EntityManagerFactory emf;

	@Override
	public boolean findEmailAndPassword(String email, String password) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		System.out.println("Created EM");

		List<AdminEntity> list = new ArrayList<AdminEntity>();
		try {
			et.begin();
			Query query = em.createNamedQuery("findByEmailAndPassword");
			query.setParameter("email", email);
			query.setParameter("password", password);

			list = query.getResultList();
			et.commit();
			return true;
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in save:" + pe.getMessage());
			et.rollback();

		} finally {
			System.out.println("Closing resources");
			em.close();
			System.out.println("Em closed");
		}

		return false;
	}

	@Override
	public AdminEntity findByEmail(String email) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		System.out.println("Created EM");
		AdminEntity ent = null;

		try {
			et.begin();
			Query query = em.createNamedQuery("findByEmail");
			query.setParameter("email", email);

			ent = (AdminEntity) query.getSingleResult();
			et.commit();
			return ent;
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in save:" + pe.getMessage());
			et.rollback();

		} finally {
			System.out.println("Closing resources");
			em.close();
			System.out.println("Em closed");
		}

		return null;
	}
}

