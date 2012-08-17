comparator
==========

`comparator` consists of utility classes that allow comparison of various free-form structured data such as HTML, XML and JSON markup. String comparison is neither sufficient nor correct, as:

* there may be white spaces
* or the attributes may be written in a different order
* tags without any data may be self-closed

And thus, the comparison needs markup analysis.

Features
--------

**0.9.0**

* Added support for XML and JSON comparison

**0.1.1**

* Support for HTML comments
* Fixed bug where attribute present in actual HTML was not present in expected HTML

**0.1.0**

* Support for comparing HTML markup

Continuous Integration
----------------------
The **library** is continuously integrated and unit tested using the *Travis CI system*.

Current status of branch `MASTER`: [![Build Status](https://secure.travis-ci.org/sangupta/comparator.png?branch=master)](http://travis-ci.org/sangupta/comparator)

The library is tested against

* Oracle JDK 7
* Oracle JDK 6
* Open JDK 7
* Open JDK 6

Versioning
----------

For transparency and insight into our release cycle, and for striving to maintain backward compatibility, 
`comparator` will be maintained under the Semantic Versioning guidelines as much as possible.

Releases will be numbered with the follow format:

`<major>.<minor>.<patch>`

And constructed with the following guidelines:

* Breaking backward compatibility bumps the major
* New additions without breaking backward compatibility bumps the minor
* Bug fixes and misc changes bump the patch

For more information on SemVer, please visit http://semver.org/.

License
-------
	
Copyright (c) 2012, Sandeep Gupta

The project uses various other libraries that are subject to their
own license terms. See the distribution libraries or the project
documentation for more details.

The entire source is licensed under the Apache License, Version 2.0 
(the "License"); you may not use this work except in compliance with
the LICENSE. You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
