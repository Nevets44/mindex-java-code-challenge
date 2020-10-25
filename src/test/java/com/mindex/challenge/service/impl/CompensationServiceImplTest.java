package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

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
import com.mindex.challenge.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String employeeUrl;
    private String compensationUrl;
    private String compensationIdUrl;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void compensationIdUrl() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        // Create checks
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        assertNotNull(createdEmployee.getEmployeeId());
        assertEmployeeEquivalence(testEmployee, createdEmployee);

        Compensation compensation = new Compensation();
        compensation.setEmployeeId(createdEmployee.getEmployeeId());
        compensation.setEmployee(createdEmployee);
        compensation.setEffectiveDate(LocalDate.now());
        compensation.setSalary(100.00);
        
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, compensation, Compensation.class).getBody();
        
        assertNotNull(createdCompensation);
        assertNotNull(createdCompensation.getEmployee().getEmployeeId());
        assertEquals(createdCompensation.getEmployee().getEmployeeId(), createdEmployee.getEmployeeId());
        
        Compensation employeeCompensation = 
        		restTemplate.getForEntity(compensationIdUrl, 
        				Compensation.class, createdCompensation.getEmployee().getEmployeeId()).getBody();
        
        assertNotNull(employeeCompensation);
        assertEquals(employeeCompensation.getEmployee().getEmployeeId(), createdCompensation.getEmployee().getEmployeeId());
    }

    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
    
    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        //assertEquals(expected.getFirstName(), actual.getFirstName());
       // assertEquals(expected.getLastName(), actual.getLastName());
       // assertEquals(expected.getDepartment(), actual.getDepartment());
        //assertEquals(expected.getPosition(), actual.getPosition());
    }
}
