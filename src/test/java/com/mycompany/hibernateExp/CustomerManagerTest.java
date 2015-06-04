/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateExp;

import com.mycompany.hibernateExp.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HEPAN
 */
public class CustomerManagerTest {
    final static Logger logger = LoggerFactory.getLogger(CustomerManagerTest.class);
    static CustomerManager customerMngr = new CustomerManager();
    
    public CustomerManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createAndStoreCustomers method, of class CustomerManager.
     */
    @Test
    public void testCreateAndStoreCustomers() {
        System.out.println("createAndStoreCustomers");
        logger.info("foobar");
        int initialCustomerCount = customerMngr.listCustomers().size();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Customer customer1 = new Customer();
        customer1.setAge(26);
        customer1.setFirstName("First");
        customer1.setLastName("Last");
        customer1.setRiskLevel(1);
        customers.add(customer1);
        customerMngr.createAndStoreCustomers(customers);
        int postCustomerCount = customerMngr.listCustomers().size();
        assertTrue("The list of customers should have grown by 1", postCustomerCount-initialCustomerCount == 1);
    }
    
    @Test
    public void testListCustomers(){
        System.out.println("list customers");
        List<Customer> customers = customerMngr.listCustomers();
        assertTrue("A list of customers should be returned", customers != null);
    }
    
}
