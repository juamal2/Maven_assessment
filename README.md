# Project Title

This is a Inventory Management system(IMS) where the user accesses a Google cloud hosted SQL database and can manipulate customers, items and orders using CRUD functionality in a java console application.
The IMS has been built using a Java Maven project and tested using junit, Mockito and jacoco. Continious Integration has been implemented using jenkins hosted on an ubuntu vm, tests are automated and the 
application is forced to clear through sonarqube quality gates before the final executable .jar is hosted on nexus. 

 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

See Below for Prerequisites that must be installed prior to exexution

```
Java Jdk 1.8 or later
Apache Maven 3.62 or later
```

### Installing

To install java 1.8 Please use the link below and follow the steps listed
```
https://java.com/en/download/help/windows_manual_download.xml
Once downloaded run the command the following command in your cmd "java -version"
If downloaded you should see java version "1.8.0_241" or a similar version of 1.8
```
To install maven 3.6.3
```
https://maven.apache.org/install.html
Once downloaded run the command the following command in your cmd "mvn -version"
If downloaded you should see your version of maven, your maven home directory and java version
```

Once Maven and Java are installed, download the project and execute the following below command
in your cmd be sure that your cmd is in the projects directory.
```
mvn install
```
Following a build success inside the newly created target folder should be a .jar with dependcies
using the cmd again execute the below command on the .jar with dependcies to run the program
```
java -jar 'FILE_NAME'
```

From here interacting with the program is simple enter your choice as shown in the terminal and the console
will guide you from there. 

## Running the tests

run the below cmd command in the project directory to execute the automated tests
```
mvn test
```

## Deployment

To deploy the project to nexus run the following command 
```
mvn deploy
```

## Built With

Maven(https://maven.apache.org/) - Dependency Management 
Jenkins https://jenkins.io/ - Continious Integration
Java https://www.java.com/en/download/ - SDK
Sonorqube https://www.sonarqube.org/ - code checker
Google Cloud Platform(GCP) https://cloud.google.com/ - cloud hosting software
Nexus Repository https://www.sonatype.com/product-nexus-repository - Repository Manager

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

Juamal Blackman Contact: Juamal.Blackman@academytrainee.com

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 


## Acknowledgments

A Big thanks to Chris Perrins and Rhys Thompson and all of QA trainers who have aided me over the past 5 weeks.
Also a just as large thank you to James Williams, Tyler Eddy, Tapiwa Tiyemba and Taiwo whos last name is way to long.
