/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateExp;

import com.mycompany.hibernateExp.domain.Car;
import org.drools.examples.decisiontable.Driver;
import org.drools.examples.decisiontable.Policy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HEPAN
 */
public class PolicyCalculatorTest {
    
    public PolicyCalculatorTest() {
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
     * Test of calculatePolicy method, of class PolicyCalculator.
     */
    @Test
    public void testCalculatePolicy() {
        System.out.println("calculatePolicy");
        Driver driver = new Driver();
        Car car = new Car();
        PolicyCalculator instance = new PolicyCalculator();
        
        Policy result = instance.calculatePolicy(driver, car);
        assertEquals(125, result.getBasePrice());
        assertEquals(20, result.getDiscountPercent());
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
