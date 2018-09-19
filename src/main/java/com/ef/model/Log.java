package com.ef.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Entity for table "Log".
 * 
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Entity(name = "log")
@DynamicUpdate(true)
public class Log {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false, name = "access_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accessDate;
	
	@Column(nullable = false, name = "ip_address")
	private String ipAddress;
	
	@Column(nullable = false, name = "access_url")
	private String accessUrl;
	
	@Column(nullable = false, name = "http_status")
	private Integer httpStatus;
	
	@Column(nullable = false, name = "access_user_agent")
	private String accessUserAgent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getAccessUserAgent() {
		return accessUserAgent;
	}

	public void setAccessUserAgent(String accessUserAgent) {
		this.accessUserAgent = accessUserAgent;
	}
	
}
