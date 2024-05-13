package com.xworkz.vManagement.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.vManagement.dto.AdminEntity;

@Repository
public class AdminRepoImpl implements AdminRepo {

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
			Query query = em.createNamedQuery("findEmailAndPassword");
			query.setParameter("email", email);
			query.setParameter("password", password);
			list = query.getResultList();
			et.commit();
			return true;
		} catch (PersistenceException pe) {

			System.out.println("PersistenceException ruuning in FindAllByAdminPortal" + pe.getMessage());
			et.rollback();
		} finally {
			System.out.println("closing resources");
			em.close();
		}
		return false;
	}

}
