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
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;
import net.htmlparser.jericho.Tag;

/**
 * Compare two given HTML strings for equality by parsing and matching the text contained
 * and ignoring the tag closing style, white spaces etc.
 * 
 * @author sangupta
 * 
 */
public class HTMLComparer {
	
	/**
	 * Compare the two {@link Reader} implementations that provide the HTML stream.
	 * 
	 * @param reader1 the first reader
	 * 
	 * @param reader2 the second reader
	 * 
	 * @return <code>true</code> if the HTML structure and values are the same, <code>false</code>
	 * otherwise
	 * 
	 * @throws IOException if there is an error reading data
	 */
	public static boolean compareHtml(Reader reader1, Reader reader2) throws IOException {
		if(reader1 == null) {
			return false;
		}
		
		if(reader2 == null) {
			return false;
		}
		
		Source source1 = new Source(reader1);
		Source source2 = new Source(reader2);
		
		return compare(source1, source2);
	}
	
	/**
	 * Compare the two {@link InputStream} implementations that provide the HTML stream.
	 * 
	 * @param stream1 the first stream
	 * 
	 * @param stream2 the second stream
	 * 
	 * @return <code>true</code> if the HTML structure and values are the same, <code>false</code>
	 * otherwise
	 * 
	 * @throws IOException if there is an error reading data
	 */
	public static boolean compareHtml(InputStream stream1, InputStream stream2) throws IOException {
		if(stream1 == null) {
			return false;
		}
		
		if(stream2 == null) {
			return false;
		}
		
		Source source1 = new Source(stream1);
		Source source2 = new Source(stream2);
		
		return compare(source1, source2);
	}
	
	/**
	 * Compare the two {@link String} representations of HTML code
	 * 
	 * @param html1 the first HTML string
	 * 
	 * @param html2 the second HTML string
	 * 
	 * @return <code>true</code> if the HTML structure and values are the same, <code>false</code>
	 * otherwise
	 */
	public static boolean compareHtml(String html1, String html2) {
		if(html1 == null) {
			return false;
		}
		
		if(html2 == null) {
			return false;
		}
		
		if(html1 == html2) {
			return true;
		}
		
		Source source1 = new Source(html1);
		Source source2 = new Source(html2);
		
		return compare(source1, source2);
	}
	
	/**
	 * Method to compare two given Jericho HTML parser {@link Source} objects that represent
	 * an AST of the parsed HTML code
	 * 
	 * @param source1 the first AST
	 * 
	 * @param source2 the second AST
	 * 
	 * @return <code>true</code> if the HTML structure and values are the same, <code>false</code>
	 * otherwise
	 */
	private static boolean compare(Source source1, Source source2) {
		List<Tag> tags1 = source1.getAllTags();
		List<Tag> tags2 = source2.getAllTags();
		
		if(tags1.size() == 0 || tags2.size() == 0) {
			if(tags1.size() != tags2.size()) {
				System.out.println("Number of tags are zero in one of the sources.");
				return false;
			}
		}
		
		// compare all elements
		int index1 = 0;
		int index2 = 0;
		
		do {
			Tag tag1 = tags1.get(index1);
			Tag tag2 = tags2.get(index2);
			
			// element names
			if(!(tag1.getName().equals(tag2.getName()))) {
				System.out.println("Tag name mismatch: tag1=" + tag1.getBegin() + "; tag2=" + tag2.getBegin());
				return false;
			}
			
			// element attributes
			if(tag1 instanceof StartTag) {
				if(!(tag2 instanceof StartTag)) {
					System.out.println("Tag not start tag: tag1=" + tag1.getBegin() + "; tag2=" + tag2.getBegin());
					return false;
				}
				
				StartTag st1 = (StartTag) tag1;
				StartTag st2 = (StartTag) tag2;
				
				// match all attributes from tag1 to tag2
				if(!testAttributes(st1, st2)) {
					return false;
				}
				
				// match all attributes from tag2 to tag1
				if(!testAttributes(st2, st1)) {
					return false;
				}
				
				// checks for self-closing tags
				boolean se1 = st1.isSyntacticalEmptyElementTag();
				boolean se2 = st2.isSyntacticalEmptyElementTag();
				
				if((se1 && !se2) || (!se1 && se2)) {
					if(!se2) {
						if(tags2.get(index2 + 1).getName().equals(tag1.getName())) {
							index2++;
						}
					} else {
						// do the other one
						if(tags1.get(index1 + 1).getName().equals(tag2.getName())) {
							index1++;
						}
					}
				}

				// element values
				String content1 = st1.getElement().getTextExtractor().setIncludeAttributes(false).toString();
				String content2 = st2.getElement().getTextExtractor().setIncludeAttributes(false).toString();
				
				content1 = StringEscapeUtils.unescapeHtml4(content1);
				content2 = StringEscapeUtils.unescapeHtml4(content2);
				
				if(!(content1.equals(content2))) {
					System.out.println("Content mismatch: tag1=" + tag1.getBegin() + "; tag2=" + tag2.getBegin());
					return false;
				}
				
			}
			
			index1++;
			index2++;
			
			if(index1 >= tags1.size()) {
				break;
			}
			
			if(index2 >= tags2.size()) {
				break;
			}
		} while(true);
		
		return true;
	}

	/**
	 * Test presence of each attribute from <code>st1</code> in <code>st2</code>. Also, the
	 * values should be identical.
	 * 
	 * @param st1
	 * @param st2
	 * @return
	 */
	private static boolean testAttributes(StartTag st1, StartTag st2) {
		List<Attribute> attributes1 = st1.getAttributes();
		
		if(attributes1.size() == 0) {
			return true;
		}
		
		for(Attribute attribute1 : attributes1) {
			String value2 = st2.getAttributeValue(attribute1.getName());
			if(value2 == null) {
				System.out.println("Attribute not present in stream2: attribute1=" + attribute1.getBegin() + "; tag2=" + st2.getBegin());
				return false;
			}
			
			String value1 = StringEscapeUtils.unescapeHtml4(attribute1.getValue());
			value2 = StringEscapeUtils.unescapeHtml4(value2);
			
			if(!(value1.equals(value2))) {
				System.out.println("Attribute value mismatch: attribute1=" + attribute1.getBegin() + "; tag2=" + st2.getBegin());
				return false;
			}
		}
		
		return true;
	}
	
}
