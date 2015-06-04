/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.droolsexp;

import com.mycompany.hibernateExp.CustomerManager;
import com.mycompany.hibernateExp.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author HEPAN
 */
@Controller
public class HibernateTestController {
    final static Logger logger = LoggerFactory.getLogger(HibernateTestController.class);
    private CustomerManager customerMngr;
    
    @RequestMapping("/customer_size")
    public @ResponseBody String getNumberOfCustomers(){
        String numOfCustomers = "N/A";
        try{
            int numOfCustomersRaw = customerMngr.listCustomers().size();
            numOfCustomers = "Number of Customers: " + numOfCustomersRaw;
        }catch(Exception ex){
            logger.error("Unable to get the number of customers", ex);
        }
        
        return numOfCustomers;
    }
    
    @RequestMapping("customer/{customerId}")
    public @ResponseBody Customer getCustomer(@PathVariable String customerId){
        Customer serve = new Customer();
        serve.setAge(20);
        serve.setFirstName("first");
        serve.setLastName("last");
        serve.setRiskLevel(3);
        logger.info("executing getCustomer with customer id of " + customerId);
        return serve;
    }

    /**
     * @return the customerMngr
     */
    CustomerManager getCustomerMngr() {
        return customerMngr;
    }

    /**
     * @param customerMngr the customerMngr to set
     */
    @Autowired
    public void setCustomerMngr(CustomerManager customerMngr) {
        this.customerMngr = customerMngr;
    }
    
    
}
