/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateExp.domain;

/**
 *
 * @author HEPAN
 */
public class Car {
    private int riskLevel = 2;

    /**
     * Empty Constructor
     */
    public Car(){}
    
    /**
     * @return the riskLevel
     */
    public int getRiskLevel() {
        return riskLevel;
    }

    /**
     * @param riskLevel the riskLevel to set
     */
    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }
    
}
