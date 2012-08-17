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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Compare two given JSON strings for equality by parsing and matching the text contained
 * and ignoring attribute ordering, white spaces etc.
 * 
 * @author sangupta
 * @since 0.2.0
 */
public class JSONComparer {
	
	/**
	 * Compare two JSON string representations.
	 * 
	 * @param json1
	 *            the first representation
	 * 
	 * @param json2
	 *            the second representation
	 * 
	 * @return <code>true</code> if the two JSON representations represent the
	 *         same object, <code>false</code> otherwise.
	 * 
	 * @throws JsonParseException
	 *             if something fails
	 * 
	 * @throws JsonProcessingException
	 *             if something fails
	 * 
	 * @throws IOException
	 *             if something fails
	 * 
	 */
	public static boolean compareJson(String json1, String json2) throws JsonProcessingException, IOException {
		if(json1 == null || json2 == null) {
			return false;
		}
		
		if(json1 == json2) {
			return true;
		}
		
		JsonFactory factory = new JsonFactory();
		factory.enable(Feature.ALLOW_COMMENTS);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node1 = mapper.readTree(json1);
		JsonNode node2 = mapper.readTree(json2);
		
		return node1.equals(node2);
	}
	
	/**
	 * Compare two JSON string representations.
	 * 
	 * @param json1
	 *            the first representation
	 * 
	 * @param json2
	 *            the second representation
	 * 
	 * @return <code>True</code> if the two JSON representations represent the
	 *         same object, <code>False</code> otherwise, and <code>null</code>
	 *         if something fails
	 */
	public static Boolean compareJsonQuietly(String json1, String json2) {
		try {
			return compareJson(json1, json2);
		} catch (JsonProcessingException e) {
			// eat up
		} catch (IOException e) {
			// eat up
		}
		
		return null;
	}
	
	/**
	 * Compare two JSON representations.
	 * 
	 * @param stream1
	 *            the first representation
	 * 
	 * @param stream2
	 *            the second representation
	 * 
	 * @return <code>true</code> if the two JSON representations represent the
	 *         same object, <code>false</code> otherwise.
	 * 
	 * @throws JsonParseException
	 *             if something fails
	 * 
	 * @throws JsonProcessingException
	 *             if something fails
	 * 
	 * @throws IOException
	 *             if something fails
	 * 
	 */
	public static boolean compareJson(InputStream stream1, InputStream stream2) throws JsonProcessingException, IOException {
		if(stream1 == null || stream2 == null) {
			return false;
		}
		
		if(stream1 == stream2) {
			return true;
		}
		
		JsonFactory factory = new JsonFactory();
		factory.enable(Feature.ALLOW_COMMENTS);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node1 = mapper.readTree(stream1);
		JsonNode node2 = mapper.readTree(stream2);
		
		return node1.equals(node2);
	}
	
	/**
	 * Compare two JSON representations.
	 * 
	 * @param stream1
	 *            the first representation
	 * 
	 * @param stream2
	 *            the second representation
	 * 
	 * @return <code>True</code> if the two JSON representations represent the
	 *         same object, <code>False</code> otherwise, and <code>null</code>
	 *         if something fails
	 */
	public static Boolean compareJsonQuietly(InputStream stream1, InputStream stream2) {
		try {
			return compareJson(stream1, stream2);
		} catch (JsonProcessingException e) {
			// eat up
		} catch (IOException e) {
			// eat up
		}
		
		return null;
	}
	
	/**
	 * Compare two JSON representations.
	 * 
	 * @param reader1
	 *            the first representation
	 * 
	 * @param reader2
	 *            the second representation
	 * 
	 * @return <code>true</code> if the two JSON representations represent the
	 *         same object, <code>false</code> otherwise.
	 * 
	 * @throws JsonParseException
	 *             if something fails
	 * 
	 * @throws JsonProcessingException
	 *             if something fails
	 * 
	 * @throws IOException
	 *             if something fails
	 * 
	 */
	public static boolean compareJson(Reader reader1, Reader reader2) throws JsonProcessingException, IOException {
		if(reader1 == null || reader2 == null) {
			return false;
		}
		
		if(reader1 == reader2) {
			return true;
		}
		
		JsonFactory factory = new JsonFactory();
		factory.enable(Feature.ALLOW_COMMENTS);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node1 = mapper.readTree(reader1);
		JsonNode node2 = mapper.readTree(reader2);
		
		return node1.equals(node2);
	}
	
	/**
	 * Compare two JSON representations.
	 * 
	 * @param reader1
	 *            the first representation
	 * 
	 * @param reader2
	 *            the second representation
	 * 
	 * @return <code>True</code> if the two JSON representations represent the
	 *         same object, <code>False</code> otherwise, and <code>null</code>
	 *         if something fails
	 */
	public static Boolean compareJsonQuietly(Reader reader1, Reader reader2) {
		try {
			return compareJson(reader1, reader2);
		} catch (JsonProcessingException e) {
			// eat up
		} catch (IOException e) {
			// eat up
		}
		
		return null;
	}

}
