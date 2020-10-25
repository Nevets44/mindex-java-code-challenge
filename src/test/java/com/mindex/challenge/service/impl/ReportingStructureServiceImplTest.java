package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

	private String employeeUrl;
    private String employeeIdUrl;
    private String reportingServiceUrl;
    
    @Autowired
    private ReportingStructureServiceImpl reportingService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
    	employeeUrl = "http://localhost:" + port + "/employee";
    	employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
    	reportingServiceUrl = "http://localhost:" + port + "/report/{id}";
    }

    @Test
    public void testCreateReadUpdate() {
    	// Create 4 employees so that have enough to conduct the test
        Employee emp1 = new Employee();
        emp1.setFirstName("John");
        emp1.setLastName("Doe");
        emp1.setPosition("Developer");
        emp1.setDepartment("Engineering");
        
        Employee emp2 = new Employee();
        emp2.setFirstName("Jane");
        emp2.setLastName("Doe");
        emp2.setPosition("Developer");
        emp2.setDepartment("Engineering");
        
        Employee emp3 = new Employee();
        emp3.setFirstName("Jock");
        emp3.setLastName("Doe");
        emp3.setPosition("Developer");
        emp3.setDepartment("Engineering");
        
        Employee emp4 = new Employee();
        emp4.setFirstName("Jake");
        emp4.setLastName("Doe");
        emp4.setPosition("Developer");
        emp4.setDepartment("Engineering");

        List<Employee> employeeList = Stream.of(emp1,emp2,emp3,emp4).collect(Collectors.toList());
        
        // Insert each employee into the database. Update the values so that the ids for 
        // each employee are populated
        for(Employee emp : employeeList ) {
        	emp = restTemplate.postForEntity(employeeUrl, emp, Employee.class).getBody();
        	assertNotNull(emp.getEmployeeId());
        }
        
        // Set the employees to the newly created employees (with ids)
        emp1 = employeeList.get(0);
        emp2 = employeeList.get(1);
        emp3 = employeeList.get(2);
        emp4 = employeeList.get(3);
        
        // Add two employees to the first employee's direct reports 
    	// and one to the second employee 
        emp1.setDirectReports(new ArrayList<Employee>(Stream.of(emp2,emp3).collect(Collectors.toList())));
        emp2.setDirectReports(new ArrayList<Employee>(Stream.of(emp4).collect(Collectors.toList())));
        
        assertEquals(emp1.getDirectReports().size(), 2);
        assertEquals(emp2.getDirectReports().size(), 1);
        
    }
}
