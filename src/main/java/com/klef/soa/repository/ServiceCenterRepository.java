package com.klef.soa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.entity.ServiceCenter;

@Repository
public interface ServiceCenterRepository extends JpaRepository<ServiceCenter, Long>
{
	List<ServiceCenter> findByName(String name);
	List<ServiceCenter> findByType(String type);
	
}
