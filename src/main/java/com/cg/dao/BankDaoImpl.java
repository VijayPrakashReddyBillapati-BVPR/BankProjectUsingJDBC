package com.cg.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cg.model.CustomersDetail;
import com.cg.utility.Database;

@Repository
public class BankDaoImpl implements BankDao {
	Database d = new Database();
	public CustomersDetail register(CustomersDetail cd) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("hibernate.cfg.xml");
		Session session = d.getSession();
    	Transaction transaction = session.beginTransaction();
    	session.saveOrUpdate(cd);
    	transaction.commit();
    	session.close();
		return cd;
	}
	public int login(CustomersDetail c) {
		// TODO Auto-generated method stub
		int accountNo = 0;
		//ApplicationContext context=new ClassPathXmlApplicationContext("hibernate.cfg.xml");		
		Session session=d.getSession();
		CustomersDetail cd = session.get(CustomersDetail.class, c.getAccountNo());
		if(cd.getPassword().equals(c.getPassword())) {
			accountNo = c.getAccountNo(); 
		}
		return accountNo;
	}

	

}
