/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.examples.decisiontable;

/**
 *
 * @author HEPAN
 */
public class Driver {
    private String name = "Mr Joe Blogs";
    private Integer age = new Integer(30);
    private Integer priorClaims = new Integer(0);
    private String locationRiskProfile = "LOW";
    private long id;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLocationRiskProfile() {
        return locationRiskProfile;
    }

    public void setLocationRiskProfile(String locationRiskProfile) {
        this.locationRiskProfile = locationRiskProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriorClaims() {
        return priorClaims;
    }

    public void setPriorClaims(Integer priorClaims) {
        this.priorClaims = priorClaims;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    protected void setId(long id) {
        this.id = id;
    }
}
