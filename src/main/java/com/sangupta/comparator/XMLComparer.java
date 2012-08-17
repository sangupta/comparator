/**
 *
 * Comparator - Utility comparison classes
 * Copyright (c) 2012, Sandeep Gupta
 * 
 * http://www.sangupta/projects/comparator
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.comparator;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Compare two given XML inputs for equality by parsing and matching the text contained
 * and ignoring the tag closing style etc.
 * 
 * @author sangupta
 * @since 0.2.0
 */
public class XMLComparer {
	
	/**
	 * Compare two XML representations
	 * 
	 * @param xml1 the first representation
	 * 
	 * @param xml2 the second representation
	 * 
	 * @return <code>true</code> if XMLs are markup equivalent, <code>false</code> otherwise.
	 * 
	 * @throws SAXException if something fails
	 * 
	 * @throws IOException if something fails
	 * 
	 * @throws ParserConfigurationException if something fails
	 * 
	 */
	public static boolean compare(String xml1, String xml2) throws SAXException, IOException, ParserConfigurationException {
		if(xml1 == null || xml2 == null) {
			return false;
		}
		
		if(xml1 == xml2) {
			return true;
		}
		
		return compare(new StringReader(xml1), new StringReader(xml2));
	}
	
	/**
	 * Compare two XML representations
	 * 
	 * @param reader1 the first representation
	 * 
	 * @param reader2 the second representation
	 * 
	 * @return <code>true</code> if XMLs are markup equivalent, <code>false</code> otherwise.
	 * 
	 * @throws SAXException if something fails
	 * 
	 * @throws IOException if something fails
	 * 
	 * @throws ParserConfigurationException if something fails
	 */
	public static boolean compare(Reader reader1, Reader reader2) throws SAXException, IOException, ParserConfigurationException {
		if(reader1 == null || reader2 == null) {
			return false;
		}
		
		if(reader1 == reader2) {
			return true;
		}
		
		return compare(new InputSource(reader1), new InputSource(reader2));
	}
	
	/**
	 * Compare two XML representations
	 * 
	 * @param source1 the first representation
	 * 
	 * @param source2 the second representation
	 * 
	 * @return <code>true</code> if XMLs are markup equivalent, <code>false</code> otherwise.
	 * 
	 * @throws SAXException if something fails
	 * 
	 * @throws IOException if something fails
	 * 
	 * @throws ParserConfigurationException if something fails
	 */
	public static boolean compare(InputSource source1, InputSource source2) throws SAXException, IOException, ParserConfigurationException {
		if(source1 == null || source2 == null) {
			return false;
		}
		
		if(source1 == source2) {
			return true;
		}
		
		DocumentBuilder db = getDocumentBuilder();
		
		Document doc1 = db.parse(source1);
		doc1.normalizeDocument();

		Document doc2 = db.parse(source2);
		doc2.normalizeDocument();

		return doc1.isEqualNode(doc2);
	}
	
	/**
	 * Compare two XML representations
	 * 
	 * @param stream1 the first representation
	 * 
	 * @param stream2 the second representation
	 * 
	 * @return <code>true</code> if XMLs are markup equivalent, <code>false</code> otherwise.
	 * 
	 * @throws SAXException if something fails
	 * 
	 * @throws IOException if something fails
	 * 
	 * @throws ParserConfigurationException if something fails
	 */
	public static boolean compare(InputStream stream1, InputStream stream2) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder db = getDocumentBuilder();
		
		Document doc1 = db.parse(stream1);
		doc1.normalizeDocument();

		Document doc2 = db.parse(stream2);
		doc2.normalizeDocument();

		return doc1.isEqualNode(doc2);		
	}
	
	/**
	 * Compare two XML representations quietly, eating up any error thrown during the process (except {@link RuntimeException}).
	 * 
	 * @param xml1 the first representation
	 * 
	 * @param xml2 the second representation
	 * 
	 * @return <code>True</code> if XMLs are markup equivalent, <code>false</code> otherwise, and <code>null</code>
	 * if something fails.
	 */
	public static Boolean compareQuietly(String xml1, String xml2) {
		try {
			return compare(xml1, xml2);
		} catch (SAXException e) {
			// eat up
		} catch (IOException e) {
			// eat up
		} catch (ParserConfigurationException e) {
			// eat up
		}
		
		return null;
	}
	
	/**
	 * Compare two XML representations quietly, eating up any error thrown during the process (except {@link RuntimeException}).
	 * 
	 * @param reader1 the first representation
	 * 
	 * @param reader2 the second representation
	 * 
	 * @return <code>True</code> if XMLs are markup equivalent, <code>false</code> otherwise, and <code>null</code>
	 * if something fails.
	 */
	public static Boolean compareQuietly(Reader reader1, Reader reader2) {
		try {
			return compare(reader1, reader2);
		} catch (SAXException e) {
			// eat up
		} catch (IOException e) {
			// eat up
		} catch (ParserConfigurationException e) {
			// eat up
		}
		
		return null;
	}
	
	/**
	 * Compare two XML representations quietly, eating up any error thrown during the process (except {@link RuntimeException}).
	 * 
	 * @param source1 the first representation
	 * 
	 * @param source2 the second representation
	 * 
	 * @return <code>True</code> if XMLs are markup equivalent, <code>false</code> otherwise, and <code>null</code>
	 * if something fails.
	 */
	public static Boolean compareQuietly(InputSource source1, InputSource source2) {
		try {
			return compare(source1, source2);
		} catch (SAXException e) {
			// eat up
		} catch (IOException e) {
			// eat up
		} catch (ParserConfigurationException e) {
			// eat up
		}
		
		return null;
	}
	
	/**
	 * Compare two XML representations quietly, eating up any error thrown during the process (except {@link RuntimeException}).
	 * 
	 * @param stream1 the first representation
	 * 
	 * @param stream2 the second representation
	 * 
	 * @return <code>True</code> if XMLs are markup equivalent, <code>false</code> otherwise, and <code>null</code>
	 * if something fails.
	 */
	public static Boolean compareQuietly(InputStream stream1, InputStream stream2) {
		try {
			return compare(stream1, stream2);
		} catch (ParserConfigurationException e) {
			// eat up
		} catch (SAXException e) {
			// eat up
		} catch (IOException e) {
			// eat up
		}
		
		return null;
	}
	
	/**
	 * Return a new instance of {@link DocumentBuilder} that will be used for comparison.
	 * 
	 * @return a new instance of {@link DocumentBuilder}
	 * 
	 * @throws ParserConfigurationException if something fails
	 */
	private static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setCoalescing(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		return dbf.newDocumentBuilder();
	}

}
