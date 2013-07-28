package com.ngo.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ngo.domain.Email;

@Repository
public class EmailDaoImpl extends AbstractDaoImpl<Email> implements EmailDao {

	@Transactional
	public Email getByStatus() {

		System.out.println("EMAIL DAO CALLED ");
		Query query = entityManager
				.createQuery("Select email from Email email where email.emailStatus =: emailStatus");
		
		query.setParameter("emailStatus", "PTS");
		
		System.out.println("EMAIL RETURMNING");

		return (Email) query.getSingleResult();
	}

}
