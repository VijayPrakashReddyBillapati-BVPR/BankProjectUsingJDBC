package com.cg.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cg.model.CustomersDetail;
import com.cg.model.TransactionDetails;
import com.cg.utility.Database;

@Repository
public class TransactionDaoImpl implements TransactionDao {
	Database d = new Database();
	
	public int deposit(int accountNo, int amt) {
		// TODO Auto-generated method stub
		int amount = 0;
		Session session = d.getSession();
		Transaction transaction = session.beginTransaction();
		CustomersDetail cd = session.get(CustomersDetail.class, accountNo);
		amount = cd.getBalance()+amt;
		cd.setBalance(amount);
		session.update(cd);
		transaction.commit();
		return amount;
	}
	
	public int withdraw(int accountNo, int amt) {
		// TODO Auto-generated method stub
		int amount = 0;
		Session session = d.getSession();
		Transaction transaction = session.beginTransaction();
		CustomersDetail cd = session.get(CustomersDetail.class, accountNo);
		if(cd.getBalance()>amt) {
			amount = cd.getBalance()-amt;
			cd.setBalance(amount);
			session.update(cd);
			transaction.commit();
		}
		return amount;
	}
	

	public int showBalance(int accountNo) {
		// TODO Auto-generated method stub
		int amount = 0;
		Session session = d.getSession();
		CustomersDetail cd = session.get(CustomersDetail.class, accountNo);
		amount = cd.getBalance();
		return amount;
	}

	public TransactionDetails fundTransfer(int accountNo, TransactionDetails transaction) {
		// TODO Auto-generated method stub
		int amount = 0;
		TransactionDetails td = null;
		Session session = d.getSession();
		Transaction trans = session.beginTransaction();
		CustomersDetail from = session.get(CustomersDetail.class, accountNo);
		CustomersDetail to = session.get(CustomersDetail.class, transaction.getToAcc());
		if(from.getBalance()>transaction.getAmt()) {
			amount = from.getBalance()-transaction.getAmt();
			from.setBalance(amount);
			to.setBalance(to.getBalance()+transaction.getAmt());
			session.update(from);
			session.update(to);
			trans.commit();
			session.close();
			td = new TransactionDetails();
			td.setFromAcc(accountNo);
			td.setToAcc(transaction.getToAcc());
			td.setAmt(amount);
			
		}
		return td;
	}

	public boolean insertTransaction(TransactionDetails transaction) {
		// TODO Auto-generated method stub
		boolean isInserted = false;
		Session session = d.getSession();
		Transaction trans = session.beginTransaction();
		if(transaction.getAmt() != 0) {
			session.update(transaction);
			trans.commit();
			isInserted = true;
		}
		
		return isInserted;
	}

	
}
