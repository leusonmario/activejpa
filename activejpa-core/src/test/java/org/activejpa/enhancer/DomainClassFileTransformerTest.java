/**
 * 
 */
package org.activejpa.enhancer;

import static org.mockito.Mockito.*;

import java.lang.instrument.IllegalClassFormatException;

import org.activejpa.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author ganeshs
 *
 */
public class DomainClassFileTransformerTest extends BaseTest {

	private DomainClassEnhancer enhancer;
	
	@BeforeMethod
	public void setup() {
		enhancer = mock(DomainClassEnhancer.class);
	}
	
	@Test
	public void shouldTransform() throws IllegalClassFormatException {
		ClassLoader loader = mock(ClassLoader.class);
		DomainClassFileTransformer transformer = new DomainClassFileTransformer(enhancer);
		transformer.transform(loader, String.class.getName(), null, null, null);
		verify(enhancer).enhance(loader, String.class.getName());
	}
}
