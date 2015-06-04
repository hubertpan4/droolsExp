/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.droolsexp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author HEPAN
 */
@Controller
public class TestController {
    
    @RequestMapping("/simple")
    public @ResponseBody String helloWorld(){
        return "helloWord";
    }
    
    @RequestMapping("/randm")
    public @ResponseBody String randomInteger(){
        return "" + java.lang.Math.round(java.lang.Math.random()*100);
    }
}
