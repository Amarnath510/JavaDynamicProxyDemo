package com.dynamicproxy;

public class CustomerBusinessService implements CustomerService {
	@Override
	public void saveCustomer() {
		
		System.out.println("Saving Customer.");
		
	}

	@Override
	public void deleteCustomer(String uid) {
		
		System.out.println("Deleting Customer.");
		
	}
}
