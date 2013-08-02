/**
 * 
 */
package com.ngo.bean;

import java.io.Serializable;

/**
 * @author shabby
 *
 */
public class EmailBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1994929136334359093L;
	/**
	 * Receiver Email Address
	 */
	private String toAddr;
	/**
	 * Email Subject
	 */
	private String subject;
	/**
	 * Email body
	 */
	
	private String body;

	/**
	 * @return the toAddr
	 */
	public String getToAddr() {
		return toAddr;
	}

	/**
	 * @param toAddr the toAddr to set
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
	 * @param subject the subject to set
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
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	
}
