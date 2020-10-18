package com.mindex.challenge.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

public class CompensationServiceImpl implements CompensationService {

	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("Creating compensation record [{}]", compensation);

		// employee.setEmployeeId(UUID.randomUUID().toString());
		// employeeRepository.insert(employee);

		return compensation;
	}

	@Override
	public Compensation read(String employeeId) {
		LOG.debug("Retrieving compensation record with employee id [{}]", employeeId);

		//Employee employee = employeeRepository.findByEmployeeId(id);

		//if (employee == null) {
		//	throw new RuntimeException("Invalid employeeId: " + id);
		//}

		return null;
	}
}
