package com.ef.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Entity for table 'LOG_AUDIT'.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Entity(name = "log_audit")
@DynamicUpdate(true)
public class LogAudit {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false, name = "ip_address")
	private String ipAddress;
	
	@Column(nullable = false, name = "threshold")
	private Integer threshold;
	
	@Column(nullable = false, name = "block_reason")
	private String blockReason;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public String getBlockReason() {
		return blockReason;
	}

	public void setBlockReason(String blockReason) {
		this.blockReason = blockReason;
	}
	
}
