package com.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class App {
public static void main(String[] args) {
		
		System.out.println("****** Java Dynamic Proxy Demo. ******");
		
		CustomerService service = new CustomerBusinessService();
		
		InvocationHandler auditHandler = new AuditLogAdvice(service);
		
		ClassLoader classLoader = CustomerService.class.getClassLoader();
		CustomerService proxy = (CustomerService) Proxy.newProxyInstance(classLoader, new Class[] {CustomerService.class}, auditHandler);
		
		proxy.saveCustomer();
		
		proxy.deleteCustomer("random id");
	}
}
