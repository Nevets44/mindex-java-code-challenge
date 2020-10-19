package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@Service
public class CompensationServiceImpl implements CompensationService {

	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("Creating compensation record [{}]", compensation);

		//compensationRepository.create(compensation);

		return compensation;
	}

	@Override
	public Compensation findByEmployeeId(String employeeId) {
		LOG.debug("Retrieving compensation record with employee id [{}]", employeeId);

		/*Compensation compensation = compensationRepository.findByEmployeeId(employeeId);
		
		if (compensation == null) {
			 throw new RuntimeException("Invalid employeeId: " + employeeId);
		}*/
		
		return null;
	}
}
