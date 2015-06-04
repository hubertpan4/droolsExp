/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.droolsexp;

import org.drools.examples.decisiontable.Driver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author HEPAN
 */
@Controller
public class DriverApiController {
    
    /**
     *
     * @param driverId
     * @return
     */
    @RequestMapping(value = "/drivers/{driverId}")
    @ResponseBody
    public Driver getDriver(@PathVariable String driverId){
        Driver driver = new Driver();
        return driver;
    }
}
