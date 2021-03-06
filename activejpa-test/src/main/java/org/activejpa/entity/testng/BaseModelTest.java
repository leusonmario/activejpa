/**
 * 
 */
package org.activejpa.entity.testng;

import java.lang.reflect.Method;

import javax.persistence.EntityTransaction;

import org.testng.IObjectFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;

/**
 * @author ganeshs
 *
 */
public class BaseModelTest {
	
	private EntityTransaction txn;

	@ObjectFactory
	public IObjectFactory getObjectFactory(ITestContext context) throws Exception {
		return new DomainClassObjectFactory();
	}
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		setup();
	}
	
	@AfterMethod
	public void afterMethod() throws Exception {
		destroy();
	}
	
	public void setup() throws Exception {
		Class<?> clazz = Class.forName("org.activejpa.entity.Model");
		Method method = clazz.getMethod("beginTxn");
		txn = (EntityTransaction) method.invoke(null);
	}
	
	public void destroy() throws Exception {
		txn.rollback();
	}
}
