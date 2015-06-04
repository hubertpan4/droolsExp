/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateExp;

import com.mycompany.hibernateExp.domain.Car;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.drools.core.io.impl.ByteArrayResource;
import org.drools.core.util.IoUtils;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.drools.examples.decisiontable.Driver;
import org.drools.examples.decisiontable.Policy;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author HEPAN
 */
@Component
public class PolicyCalculator {
    final static Logger logger = LoggerFactory.getLogger(PolicyCalculator.class);
    private KnowledgeBase kbase;
    
    /**
     * Calculate the policy for a given driver and an associated car
     * @param driver
     * @param car
     * @return 
     */
    public Policy calculatePolicy(Driver driver, Car car){
        //create empty policy
        Policy policy = new Policy();
        
        //create new Knowledge Session
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        
        //insert known facts
        ksession.insert(driver);
        ksession.insert(policy);
        ksession.insert(car);
        
        //Perform Business Logic
        ksession.fireAllRules();
        
        //clean up the Knowldge Session
        ksession.dispose();
        
        logger.debug("BASE PRICE IS: " + policy.getBasePrice());
        logger.debug("DISCOUNT IS: " + policy.getDiscountPercent());
        
        return policy;
    }
    
    /**
     * 
     */
    public PolicyCalculator(){
        kbase = this.buildKBase();
    }
    
    
    
    /**
     * Creates a new kbase containing the rules generated from the xls file and
     * the templates.
     *
     * @return
     * @throws IOException
     */
    private KnowledgeBase buildKBase() {
        //first we compile the decision table into a whole lot of rules.
        final ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();

        String basePricingDRL = null;
        String promotionalPricingDRL = null;
        try {
            //the data we are interested in starts at row 10, column 3
            basePricingDRL = converter.compile(getSpreadsheetStream(), getBasePricingRulesStream(), 10, 3);
            //the data we are interested in starts at row 30, column 3
            promotionalPricingDRL = converter.compile(getSpreadsheetStream(), getPromotionalPricingRulesStream(), 42, 3);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid spreadsheet stream.", e);
        }

        //compile the drls
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(new ByteArrayResource(basePricingDRL.getBytes(IoUtils.UTF8_CHARSET)), ResourceType.DRL);
        kbuilder.add(new ByteArrayResource(promotionalPricingDRL.getBytes(IoUtils.UTF8_CHARSET)), ResourceType.DRL);

        //compilation errors?
        if (kbuilder.hasErrors()) {
            System.out.println("Error compiling resources:");
            Iterator<KnowledgeBuilderError> errors = kbuilder.getErrors().iterator();
            while (errors.hasNext()) {
                System.out.println("\t" + errors.next().getMessage());
            }
            throw new IllegalStateException("Error compiling resources");
        }

        //Uncomment to see the base pricing rules
        logger.debug(basePricingDRL);
        //Uncomment to see the promotional pricing rules
        logger.debug(promotionalPricingDRL);
        //BUILD KBASE
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        return kbase;

    }
    
    private InputStream getSpreadsheetStream() throws IOException {
        return ResourceFactory.newClassPathResource("org/drools/examples/decisiontable/ExamplePolicyPricingCar.xls").getInputStream();
    }

    private InputStream getBasePricingRulesStream() throws IOException {
        return ResourceFactory.newClassPathResource("org/drools/examples/decisiontable/BasePricingCar.drt").getInputStream();
    }

    private InputStream getPromotionalPricingRulesStream() throws IOException {
        return ResourceFactory.newClassPathResource("org/drools/examples/decisiontable/PromotionalPricing.drt").getInputStream();
    }
}
