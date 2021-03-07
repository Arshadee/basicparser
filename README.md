# basicparser
Code Challenge : Basic parser convert a string expresion into an object tree

##Given any string expression for example r(a()b(c()d(e()))) following this format.

**Parsing:**
* Convert the string expression of node and their relations into a hierarchical,
  tree structure, depicting their relations parent + children.
* Display the expression tree

For example :

string expression r(a()b(c()d()))

Your output should be: (or similar representing the node tree)
Node Tree
 r
 | - a
 | - b
     | - c
     | - d
         | - e

**Validation:**
* Must validate the string expression - parenthesis must be balanced
  no missing open or closed brackets.
* Each Node must be 1 char and followed by open bracket thats eventually closed
* No brackets not following i.e. belonging to a node.
* Expression cannot represent a disjoint tree and / or have a missing Root Node

#This project is the solution for this code challenge, Basic Parser

##To clone


## Build / Compile and Run the application using my scripts
In the main project folder, **basicParser**, run the following: on macos / unix
**chmod +rx buildRun.sh**
**./buildRun.sh**

## Run the application only using my scripts
In the main project folder, **basicParser**, run the following: on mac-os / unix operating systems
**chmod +rx run.sh**
.**/run.sh**

## Build / Compile the application using maven
In command-Line / term navigate to the main project folder, **basicParser**,
and run : **mvn clean install** 

## Run the application  
In command-Line / term navigate to the main project folder, **basicParser**,
and run : **java -jar target/basicParser.jar** 
