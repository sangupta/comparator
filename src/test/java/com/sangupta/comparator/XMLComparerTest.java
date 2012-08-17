package com.sangupta.comparator;

import junit.framework.Assert;

import org.junit.Test;

public class XMLComparerTest {
	
	@Test
	public void testXMLComparer() {
		Assert.assertFalse(XMLComparer.compareQuietly((String) null, (String) null));
		Assert.assertFalse(XMLComparer.compareQuietly("", null));
		Assert.assertFalse(XMLComparer.compareQuietly(null, ""));
		
		Assert.assertTrue(XMLComparer.compareQuietly("<hello />", "<hello />"));
		Assert.assertTrue(XMLComparer.compareQuietly("<hello />", "<hello></hello>"));
		Assert.assertFalse(XMLComparer.compareQuietly("<hello />", "<hello> </hello>"));
		
		Assert.assertTrue(XMLComparer.compareQuietly("<hello mode=\"test\" />", "<hello mode=\"test\"></hello>"));
	}

}
