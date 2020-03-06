#Spring + JPA + Swagger

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


###_ Setup / Pre-Requirements_

* *MS SQL Server 2017 Express Edition

>Run the files/scripts .sql found in the folder "src/main/resources/database"


``` 
1) Tables.sql
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
	
   `sqlcmd -S localhost,1433 -U SA -P *<<SET_YOUR_PASSWORD>>*`
   ![Alt text](./doc/sqlcmd.png?raw=true "sqlcmd connect SqlServerContainer")

5. **Check Connection**
		
   `select @@version`	
	![Alt text](./doc/sqlcmd_check.png?raw=true "check SqlServerContainer")
	
6. **Create tables to run our project**

   `Run the files/scripts .sql found in the folder "src -> main -> resources -> database"`
	
   `sqlcmd -S localhost,1433 -U SA -P *<<SET_YOUR_PASSWORD>>* -i <PATH>\Tables.sql -o <PATH>\output_Tables.txt`
	
   `sqlcmd -S localhost,1433 -U SA -P *<<SET_YOUR_PASSWORD>>* -i <PATH>\DML.sql -o <PATH>\DML.txt`
	![Alt text](./doc/sqlcmd_tables.png?raw=true "check SqlServerContainer")


###_ Update file application.properties_


		###### Server ######
		server.port=8081
		###### Datasource ######
		spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
		spring.datasource.url=jdbc:sqlserver://localhost;databaseName=Investment
		spring.datasource.username=<<SET_YOUR_USERNAME>>
		spring.datasource.password=<<SET_YOUR_PASSWORD>>


###_ Install maven dependencies_ 

	mvn clean install
   ![Alt text](./doc/mvn_cleaninstall.png?raw=true "mvn clean install")


###_ Build and start the application_

   Access swagger interface and test 
	
   `http://localhost:8081/swagger-ui.html`

   ![Alt text](./doc/swagger.png?raw=true "Swagger")

	