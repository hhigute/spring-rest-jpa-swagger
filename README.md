hi

test1

test2

test3

test5

test6
--

x
Y
Z

--
+++



# Spring + JPA + Swagger

In this example we create crud operations using Spring + JPA + Swagger

I used [Spring Boot](https://start.spring.io) to create the base project.

_Dependencies:_

* Spring Web
* Spring Data JPA

Bellow others Maven dependencies that I configured manually.

	<!-- MS-SQLServer -->
	<dependency>
		<groupId>com.microsoft.sqlserver</groupId>
		<artifactId>mssql-jdbc</artifactId>
		<version>8.2.0.jre8</version>
	</dependency>
	<!-- Swagger -->
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger2</artifactId>
		<version>2.9.2</version>
	</dependency>
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger-ui</artifactId>
		<version>2.8.0</version>
	</dependency>
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-bean-validators</artifactId>
		<version>2.8.0</version>
	</dependency>


### _Setup / Pre-Requirements_

* *MS SQL Server 2017 Express Edition

>Run the files/scripts .sql found in the folder "src/main/resources/database"


```
1) DDL.sql
2) DML.sql
```

* If you don't have or don't want to install SQLServer, you can follow the steps bellow to create your database in `Docker` container.



1. **Start Docker**

   In my case I used Docker Desktop for windows

2. **Create MS SQL Server container**

   Run the command bellow in Prompt Ms-DOS </br>
   PS: Password must be strong </br></br> `docker run -d -p 1433:1433 -e sa_password=<<SET_YOUR_PASSWORD>> -e ACCEPT_EULA=Y --name SqlServerContainer microsoft/mssql-server-windows-express`

![Alt text](./doc/dockerrun.png?raw=true "docker SqlServerContainer")

3. **Check Installation**

   `docker ps -a`

   ![Alt text](./doc/dockerps-a.png?raw=true "docker ps -a")

4. **Connect to SqlServerContainer**

   `sqlcmd -S localhost,1433 -U SA -P <<SET_YOUR_PASSWORD>>`

   ![Alt text](./doc/sqlcmd.png?raw=true "sqlcmd connect SqlServerContainer")

5. **Check Connection**

   `select @@version`

   ![Alt text](./doc/sqlcmd_check.png?raw=true "check SqlServerContainer")

6. **Create tables to run our project**

   `Run the files/scripts .sql found in the folder "src -> main -> resources -> setup"`

   `sqlcmd -S localhost,1433 -U SA -P <<SET_YOUR_PASSWORD>> -i <PATH>\DDL.sql -o <PATH>\output_DDL.txt`

   `sqlcmd -S localhost,1433 -U SA -P <<SET_YOUR_PASSWORD>> -i <PATH>\DML.sql -o <PATH>\output_DML.txt`

   ![Alt text](./doc/sqlcmd_tables.png?raw=true "check SqlServerContainer")


### _Update file application.properties_


		###### Server ######
		server.port=8081
		###### Datasource ######
		spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
		spring.datasource.url=jdbc:sqlserver://localhost;databaseName=Investment
		spring.datasource.username=<<SET_YOUR_USERNAME>>
		spring.datasource.password=<<SET_YOUR_PASSWORD>>


### _Install maven dependencies_

	mvn clean install
   ![Alt text](./doc/mvn_cleaninstall.png?raw=true "mvn clean install")


### _Build and start the application_

   Access swagger interface and test

   `http://localhost:8081/swagger-ui.html`

   ![Alt text](./doc/swagger.png?raw=true "Swagger")


### _Deploy Application in Docker Container_

   To deploy this application in Docker container you should have to follow the steps bellow.

   * Adjust pom.xml to generate the project .jar file (tag <packaging>)

	<packaging>jar</packaging>

   * Clean and install package with pom.xml adjusts

	mvn clean install


   * Create DockerFile (root project)

	FROM java:8
	EXPOSE 7001
	ADD /target/spring-rest-jpa-swagger-0.0.1-SNAPSHOT.jar spring-rest-jpa-swagger.jar
	ENTRYPOINT ["java", "-jar", "spring-rest-jpa-swagger.jar"]

   * Create project image

	docker build -f DockerFile -t spring-rest-jpa-swagger .

   ![Alt text](./doc/docker_create_image.png?raw=true "Create docker image")


   * Start/run application
 	Ps: The command bellow will allow the application at port 7001 and route to port 8081 internally (port configured within application.properties)

	docker run -p 7001:8081 spring-rest-jpa-swagger 	

   ![Alt text](./doc/docker_start_application.png?raw=true "Start docker image")

   * Check docker image

	docker ps -f ancestor=spring-rest-jpa-swagger

   ![Alt text](./doc/docker_check_deploy.png?raw=true "Check image")
