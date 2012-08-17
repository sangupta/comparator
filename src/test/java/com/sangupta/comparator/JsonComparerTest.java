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

import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit tests for {@link JSONComparer} utility class.
 * 
 * @author sangupta
 * @since 0.2.0
 */
public class JsonComparerTest {
	
	@Test
	public void testJsonComparer() {
		Assert.assertFalse(JSONComparer.compareJsonQuietly((String) null, null));
		Assert.assertFalse(JSONComparer.compareJsonQuietly("", null));
		Assert.assertFalse(JSONComparer.compareJsonQuietly(null, ""));
		Assert.assertTrue(JSONComparer.compareJsonQuietly("{ \"short_url\": \"http://drbl.in/hZx\", \"id\": 21 }", "{ \"id\": 21, \"short_url\": \"http://drbl.in/hZx\" }"));
	}

}
