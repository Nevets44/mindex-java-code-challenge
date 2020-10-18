package com.mindex.challenge.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

public class ReportingStructureImpl implements ReportingStructureService {

	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

	@Override
	public ReportingStructure read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
