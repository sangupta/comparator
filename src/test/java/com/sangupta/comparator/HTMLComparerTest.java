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

import org.junit.Assert;
import org.junit.Test;

public class HTMLComparerTest {

	@Test
	public void testHTMLComparer() {
		String html1 = "<b><i>Some</i><hr></hr> more</b>";
		String html2 = " <b><i>Some</i><hr/> more</b>";
		
		Assert.assertTrue(HTMLComparer.compareHtml(html1, html2));
	}

}
