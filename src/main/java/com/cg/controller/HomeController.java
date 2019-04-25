package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cg.utility.Database;
import com.cg.model.CustomersDetail;
import com.cg.model.TransactionDetails;
import com.cg.service.BankService;
import com.cg.service.TransactionService;
@RestController
public class HomeController {
	Database d=new Database();
	int accountNo;
	@Autowired
	BankService bank;
	@Autowired
	TransactionService tService;
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String start() {
		
		return "Loading...";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity register(@RequestBody CustomersDetail customer) {
		bank.register(customer);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void login(@RequestBody CustomersDetail c) {
		accountNo = bank.login(c);
		
	}
	
	@RequestMapping(value="/deposit",method=RequestMethod.PUT)
	public int deposit(@RequestBody int amt) {
		int amount = 0;
		if(accountNo != 0) {
			amount = tService.deposit(accountNo,amt);
		}
		return amount;
	}
	
	@RequestMapping(value="/withdraw",method=RequestMethod.PUT)
	public int withdraw(@RequestBody int amt) {
		int amount = 0;
		if(accountNo != 0) {
			amount = tService.withdraw(accountNo,amt);
		}
		
		return amount;
		
	}
	
	@RequestMapping(value="/showbalance",method=RequestMethod.GET)
	public int showBalance() {
		int amount = 0;
		if(accountNo != 0) {
			amount = tService.showBalance(accountNo);
		}
		return amount;
		
	}
	
	@RequestMapping(value="/fundtransfer",method=RequestMethod.PUT)
	public int fundTransfer(@RequestBody TransactionDetails transaction) {
		int amount = 0;
		if(accountNo != 0) {
			TransactionDetails transactionObj = new TransactionDetails();
			transactionObj= tService.fundTransfer(accountNo,transaction);
			boolean isInserted = tService.insertTransaction(transactionObj);
		}
		
		return amount;
	}
	
	
}
