package com.ngo.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author shabby
 * 
 */
@Entity
@Table(name = "EMAIL")
public class Email implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4338011159819680895L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EMAIL_SEQ")
	@SequenceGenerator(name = "EMAIL_SEQ", sequenceName = "EMAIL_SEQ")
	private long id;

	@Column(name = "TO_ADDR")
	private String toAddr;

	@Column(name = "SUBJECT")
	private String subject;

	@Column(name = "BODY")
	private String body;

	@Column(name = "EMAIL_STAUS")
	private String emailStatus;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the toAddr
	 */
	public String getToAddr() {
		return toAddr;
	}

	/**
	 * @param toAddr
	 *            the toAddr to set
	 */
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the emailStatus
	 */
	public String getEmailStatus() {
		return emailStatus;
	}

	/**
	 * @param emailStatus
	 *            the emailStatus to set
	 */
	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

}
