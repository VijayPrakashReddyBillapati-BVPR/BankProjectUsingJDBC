package com.cg.dao;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;

import com.cg.model.CustomersDetail;

public interface BankDao {
	public CustomersDetail register(@RequestBody CustomersDetail cd);
	public int login(CustomersDetail c);
}
