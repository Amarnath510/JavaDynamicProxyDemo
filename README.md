# Java Dynamic Proxy Tutorial

**Dynamic Proxy** <br />
  A dynamic proxy class (simply referred to as a proxy class below) is a class that implements a list of interfaces specified at runtime when the class is created, with behavior as described below. A proxy interface is such an interface that is implemented by a proxy class. A proxy instance is an instance of a proxy class. Each proxy instance has an associated invocation handler object, which implements the interface InvocationHandler.
  
**Scenario** <br />
  Consider you have a method where you need to print logging before and after the method. You can do this by writing logging in the method itself.
  In this scenario we have both the business logic and the logging here. This violates Single Responsibility Principle (in SOLID) where a class should have only one responsibility.
  So how do we separate them?
  
**Static Proxy** <br />
  Create a Proxy class specific to this class and this class method will invoke the actual class method. <br />
  [CustomerBusinessServiceProxy](https://github.com/Amarnath510/JavaDynamicProxyDemo/blob/master/src/com/dynamicproxy/CustomerBusinessServiceProxy.java) class acts as a proxy for 
  [CustomerBusinessService](https://github.com/Amarnath510/JavaDynamicProxyDemo/blob/master/src/com/dynamicproxy/CustomerBusinessService.java) such that
  we add the logging in the Proxy class. Hence we have separated the logging responsibility from the method which has business.
  
**Problem** <br />
  The problem with static proxy approach is, we need to create a lot of proxy classes which make tough to maintain code.
  Hence we move Dynamic Proxy.
  
**Java Dynamic Proxy Implementation**
  - We will create a dynamic proxy by implementing [InvocationHandler](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/InvocationHandler.html) interface.
  - Create [AuditLogAdvice](https://github.com/Amarnath510/JavaDynamicProxyDemo/blob/master/src/com/dynamicproxy/AuditLogAdvice.java) which implements Invocationhandler interface.
  - Implement method [invoke](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/InvocationHandler.html#invoke(java.lang.Object,%20java.lang.reflect.Method,%20java.lang.Object[])).
    It has the following parameters, <br />
    ```Object invoke(Object proxy, Method method, Object[] args)``` <br />
    proxy = the proxy instance that the method was invoked on. <br />
    method = the method on which we need to run the proxy. <br />
    args = arguments that we send to the method.
  - How do we create proxy?
  
    ```java
     CustomerService service = new CustomerBusinessService();
		 InvocationHandler auditHandler = new AuditLogAdvice(service);
		 ClassLoader classLoader = CustomerService.class.getClassLoader();
		 CustomerService proxy = (CustomerService) Proxy.newProxyInstance(classLoader, new Class[] {CustomerService.class}, auditHandler);
    ```
  
    First create the class instance where we have the method for which we need to have logging. <br />
    Create an instance of the InvocationHandler interface using AuditLogAdvice. <br />
    Finally create proxy using [newProxyInstance](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Proxy.html#newProxyInstance(java.lang.ClassLoader, java.lang.Class[], java.lang.reflect.InvocationHandler)) by passing class loader 
    and giving the interface along with the InvocationHandler instance.
  - Finally make the call to the method using the dynamic CustomerService instance.
   
     ```
      // save customer
      proxy.saveCustomer();
      
      // delete customer
      proxy.deleteCustomer("random id");
     ```

**Credits** <br />
 [Class Proxy](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Proxy.html)
