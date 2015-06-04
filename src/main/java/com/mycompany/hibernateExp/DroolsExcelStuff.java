/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateExp;

import org.drools.examples.decisiontable.Driver;
import org.drools.examples.decisiontable.Policy;
import org.drools.core.util.IoUtils;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.drools.core.io.impl.ByteArrayResource;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.api.io.ResourceType;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
/**
 *
 * @author HEPAN
 */
public class DroolsExcelStuff {
    public static void main(String[] args) {
        DroolsExcelStuff launcher = new DroolsExcelStuff();
        launcher.executeExample();
    }

    private int executeExample() {

        //BUILD THE KBASE
        KnowledgeBase kbase = this.buildKBase();

        //GET A KSESSION
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        //now create some test data
        Driver driver = new Driver();
        Policy policy = new Policy();

        ksession.insert(driver);
        ksession.insert(policy);
        
        ksession.fireAllRules();

        System.out.println("BASE PRICE IS: " + policy.getBasePrice());
        System.out.println("DISCOUNT IS: " + policy.getDiscountPercent());

        ksession.dispose();

        return policy.getBasePrice();

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
            promotionalPricingDRL = converter.compile(getSpreadsheetStream(), getPromotionalPricingRulesStream(), 30, 3);
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
        System.out.println(basePricingDRL);
        //Uncomment to see the promotional pricing rules
        System.out.println(promotionalPricingDRL);
        //BUILD KBASE
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        return kbase;

    }

    private InputStream getSpreadsheetStream() throws IOException {
        return ResourceFactory.newClassPathResource("org/drools/examples/decisiontable/ExamplePolicyPricing.xls").getInputStream();
    }

    private InputStream getBasePricingRulesStream() throws IOException {
        return ResourceFactory.newClassPathResource("org/drools/examples/decisiontable/BasePricing.drt").getInputStream();
    }

    private InputStream getPromotionalPricingRulesStream() throws IOException {
        return ResourceFactory.newClassPathResource("org/drools/examples/decisiontable/PromotionalPricing.drt").getInputStream();
    }
}
