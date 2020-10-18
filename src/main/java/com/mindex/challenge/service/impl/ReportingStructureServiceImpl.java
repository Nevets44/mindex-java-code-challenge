package com.mindex.challenge.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    private int calculateNumOfReports(Employee emp) {
    	int numOfReports = emp.getDirectReports() == null ? 0 : emp.getDirectReports().size();
    	
    	if(numOfReports > 0)
    		for(Employee e : emp.getDirectReports())
    			numOfReports += calculateNumOfReports(e);
    	
    	return numOfReports;
    }
    
	@Override
	public ReportingStructure read(String id) {
		LOG.debug("Retrieving report with employee id [{}]", id);
		
		Employee employee = employeeRepository.findByEmployeeId(id);
		
		if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
		
		ReportingStructure rs = new ReportingStructure();
		rs.setEmployee(employee);
		rs.setNumberOfReports(calculateNumOfReports(employee));
	
		return rs;
	}

}
