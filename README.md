# basicparser
**Code Challenge : Basic parser convert a string expresion into an object tree**

**Convert any given string expression made up of nodes/elements each containing an opening and closing bracket.
a node may contain other nodes with in it brackets,f its very simple format
for example r(a()b(c()d(e())))**

**Parsing:**
* Convert the string expression of nodes and their relations into a hierarchical,
  tree structure, depicting their parent + children relations.<br>
   * Must apply Validation Rules stated below.
* Display the expression tree

For example :

**string expression r(a()b(c()d())e())**

Your **output** should be: (or similar representing the node tree)

**Node Tree**<br>

 **r**<br>
 **|--a**<br>
 **|--b**<br>
 **|--|--c**<br>
 **|--|--d**<br>
 **|--e**<br>

**Validation Rules:**
* Must validate the string expression - parenthesis must be balanced
  no missing open or closed brackets.
* Each node must be followed by open bracket thats eventually closed.
* No brackets that do not follow a node i.e. belonging to a node.
* Expression cannot represent a disjoint tree and / or have a missing Root Node.
* No cyclic relations. i.e. no node can be duplicated in a given string expression

# This project is the solution for this code challenge, Basic Parser

## To clone
https://github.com/Arshadee/basicparser.git

## Build / Compile and Run the application using my scripts
In the main project folder, **basicParser**, run the following: on mac-os / unix operating systems</br>
**chmod +rx buildRun.sh**  <i> - Initially to make executable</i></br>
**./buildRun.sh** <i> - To run script</i>

## Run the application only using my scripts
In the main project folder, **basicParser**, run the following: on mac-os / unix operating systems</br>
**chmod +rx run.sh** <i> - Initially to make executable</i></br>
**./run.sh** <i> - To run script</i>

## Build / Compile the application using maven
In command-Line / term navigate to the main project folder, **basicParser**,
and run : **mvn clean install**

## Run the application  
In command-Line / term navigate to the main project folder, **basicParser**,
and run : **java -jar target/basicParser.jar**
