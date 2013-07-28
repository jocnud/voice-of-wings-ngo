package com.ngo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ngo.dao.EmailDao;
import com.ngo.domain.Email;

@Service
public class EmailServiceImpl implements EmailService{


	@Autowired
	private EmailDao emailDao;  

	@Override
	@Transactional
	public void save(Email email) {
		System.out.println("in service");
		this.emailDao.save(email);
		
	}

}
