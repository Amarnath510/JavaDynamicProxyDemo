package com.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AuditLogAdvice implements InvocationHandler {
	private CustomerService target;
	
	public AuditLogAdvice(CustomerService target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("\nAudit Log Start: " + method.getName());
		
		// To print input parameters.
		System.out.println("Input Values = " + Arrays.toString(args));
		
		Object result = method.invoke(target, args);
		
		System.out.println("Audit Log End: " + method.getName());
		
		return result;
	}
}
