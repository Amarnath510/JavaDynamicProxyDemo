package com.dynamicproxy;


//Static Proxy creation. This proxy is only for CustomerBusinessServiceProxy.
//But in reality we can't create Proxy for each of the Business logic methods or classes.
//It becomes tough to maintain all the Proxies.
public class CustomerBusinessServiceProxy implements CustomerService {
	
	private CustomerService service;
	
	public CustomerBusinessServiceProxy(CustomerService service) {
		this.service = service;
	}
	
	@Override
	public void saveCustomer() {
		System.out.println("Called saveCustomer() Method with Input bla bla..");
		
		service.saveCustomer();
		
		System.out.println("Completed saveCustomer() Method with Input bla bla..");
	}

	@Override
	public void deleteCustomer(String uid) {
		
		System.out.println("Called deleteCustomer() Method with Input bla bla..");
		
		service.saveCustomer();
		
		System.out.println("Completed deleteCustomer() Method with Input bla bla..");
		
	}
}
