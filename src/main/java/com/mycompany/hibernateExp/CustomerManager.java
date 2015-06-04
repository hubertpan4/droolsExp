/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateExp;

import com.mycompany.hibernateExp.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 *
 * @author HEPAN
 */
@Component
public class CustomerManager {
    
    public void createAndStoreCustomers(ArrayList<Customer> customers){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Customer customer : customers){
            session.save(customer);
        }
        session.getTransaction().commit();
    }
    
    public List<Customer> listCustomers(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String query = "from Customer";
        List result = session.createQuery(query).list();
        session.getTransaction().commit();
        return result;
    }
}
