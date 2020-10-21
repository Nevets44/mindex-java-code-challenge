package com.mindex.challenge.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mindex.challenge.data.Compensation;

@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
	@SuppressWarnings("unchecked")
	Compensation save(Compensation compensation);
	Compensation findByEmployeeEmployeeId(String employeeId);
}
