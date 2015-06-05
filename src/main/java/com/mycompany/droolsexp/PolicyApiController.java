/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.droolsexp;

import com.mycompany.hibernateExp.PolicyCalculator;
import com.mycompany.hibernateExp.domain.Car;
import org.drools.examples.decisiontable.Driver;
import org.drools.examples.decisiontable.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author HEPAN
 */
@Controller
public class PolicyApiController {
    private PolicyCalculator policyCalculator;
    
    @RequestMapping(value="/policies/temp/{driverId}/{carId}")
    public @ResponseBody Policy calculatePolicyForDriverCarPair(String driverId, String carId){
        Driver driver = new Driver();
        Car car = new Car();
        
        return this.calculatePolicyForDriverCarPair(driver, car);
    }
    
    public Policy calculatePolicyForDriverCarPair(Driver driver, Car car){
        Policy policy = new Policy();
        
        policy = policyCalculator.calculatePolicy(driver, car);
        
        return policy;
    }

    /**
     * @return the policyCalculator
     */
    public PolicyCalculator getPolicyCalculator() {
        return policyCalculator;
    }

    /**
     * @param policyCalculator the policyCalculator to set
     */
    @Autowired
    public void setPolicyCalculator(PolicyCalculator policyCalculator) {
        this.policyCalculator = policyCalculator;
    }
}
