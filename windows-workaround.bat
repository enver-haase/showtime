@echo off
REM Some operating systems are more equal than others.

start call windows-start-tests.bat

SET MAVEN_OPTS=-Ddatabase.backend=com.infraleap.demo.tests.database.TestDB
mvn -Pci-tests jetty:run
