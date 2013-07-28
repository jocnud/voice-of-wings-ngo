package com.ngo.dao;

import com.ngo.domain.Email;

/**
 * 
 * @author shabby
 *
 */
public interface EmailDao {
	
	/**
	 * s
	 * @param email
	 */
	public void save(Email email);

	public Email getByStatus();

}
