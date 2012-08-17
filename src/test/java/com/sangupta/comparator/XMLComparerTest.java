package com.sangupta.comparator;

import junit.framework.Assert;

import org.junit.Test;

public class XMLComparerTest {
	
	@Test
	public void testXMLComparer() {
		Assert.assertFalse(XMLComparer.compareXmlQuietly((String) null, (String) null));
		Assert.assertFalse(XMLComparer.compareXmlQuietly("", null));
		Assert.assertFalse(XMLComparer.compareXmlQuietly(null, ""));
		
		Assert.assertTrue(XMLComparer.compareXmlQuietly("<hello />", "<hello />"));
		Assert.assertTrue(XMLComparer.compareXmlQuietly("<hello />", "<hello></hello>"));
		Assert.assertFalse(XMLComparer.compareXmlQuietly("<hello />", "<hello> </hello>"));
		
		Assert.assertTrue(XMLComparer.compareXmlQuietly("<hello mode=\"test\" />", "<hello mode=\"test\"></hello>"));
	}

}
