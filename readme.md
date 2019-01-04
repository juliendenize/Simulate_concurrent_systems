Group:
- Nom1 Prenom1 (to be adapted)
- Nom2 Prenom2 (to be adapted)

Copyright (C) 2016-2017
Contact: Denis.Conan[at]telecom-sudparis.eu

License: See file LICENSE.txt

================================================================================

Software prerequisites:
-----------------------
	1. Java Version >= 8.0
	   (http://www.oracle.com/technetwork/java/javase/overview/index.html)
	2. Maven Version >= 3.3.9
	   (http://maven.apache.org/)

Shell variables to set in your ~/.bashrc file:
----------------------------------------------
	1. JAVA_HOME to your Java SDK
$
export JAVA_HOME=<the root directory of your Java installation>
echo $JAVA_HOME

Before using the Maven module:
------------------------------
Modify the line 
 <artifactId>login1-login2</artifactId>
in the file Code/pom.xml

Compilation and installation:
-----------------------------
	To compile and install the modules, execute the following command.
	Use the options -Dmaven.test.skip=true and -Dmaven.javadoc.skip=true
	if you do not want to execute the JUnit tests or to generate the
	JavaDoc files, so that the compilation is much more rapid.
$
(cd Code; mvn install)
$

Execution:
-----------------------------
	To launch main class, execute the following command.
$
(cd Code; mvn exec:java)
$

In Eclipse:
-----------
	To load the project in Eclipse, either use the maven plugin (File >
	Import > Maven > Existing maven project), or create the Eclipse project
	using the following command and then create a Java project in Eclipse:
$
(cd Code; mvn eclipse:clean eclipse:eclipse)
$
