# Console App

This is a simple console application which takes a directory path as argument.

## Requirements
| [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) | [Apache Maven 3.8.4 ](https://maven.apache.org/) | [Git](https://git-scm.com/) |


### Running locally

### Step 1 - Checkout

```bash
git clone https://github.com/davimonteiro/console-app
cd console-app
```  

### Step 2 - Packaging

```bash
mvn clean compile package
``` 

### Step 3 - Running


```bash
java -jar target/console-app-1.0-SNAPSHOT-jar-with-dependencies.jar "input path"
```