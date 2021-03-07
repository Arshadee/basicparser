#!/bin/bash
mvn clean install
cd target/
java -jar basicParser.jar
