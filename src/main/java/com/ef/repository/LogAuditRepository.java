package com.ef.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ef.model.LogAudit;

/**
 * Repository for table 'LOG_AUDIT'.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Repository
public interface LogAuditRepository extends JpaRepository<LogAudit, Integer>{
}
