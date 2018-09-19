package com.ef.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ef.model.Log;

/**
 * Repository for table 'LOG'.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Integer>{

	@Query("SELECT ipAddress, COUNT(ipAddress) FROM log WHERE accessDate between ?1 and ?2 GROUP BY ipAddress HAVING COUNT(ipAddress) >= ?3")
	List<Object[]> countByIpAddress(Date from, Date to, Long threshold);
}
