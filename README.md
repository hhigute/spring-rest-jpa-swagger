# spring-rest-jpa-swagger

In this example we create crud operations using Spring Boot + JPA + Swagger 

# Setup / Pre-Requirements

* MS SQL Server 2017 Express Edition

		Run the files/scripts .sql found in the folder "src -> main -> resources -> database"
		1) Tables.sql
		2) DML.sql

* If you don't have or don't want to install SQLServer, you can use Docker to create your database.

1) Start docker 
2) Run the command bellow in Prompt Ms-Dos
	
		docker run -d -p 1433:1433 -e sa_password=<<SET_YOUR_PASSWORD>> -e ACCEPT_EULA=Y --name SqlServerContainer microsoft/mssql-server-windows-express
		
		PS: Password must be strong
		
3) Check Installation
	
		docker ps -a
		
4) Connecting to SqlServer	
	
		sqlcmd -S localhost,1433 -U SA -P <<SET_YOUR_PASSWORD>>

5) Check Connection
		
		select @@version; 
		go	
	
6) Run the files/scripts .sql found in the folder "src -> main -> resources -> database"

		- Tables.sql
		- DML.sql


# Update file application.properties

		###### Server ######
		server.port=8081
		###### Datasource ######
		spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
		spring.datasource.url=jdbc:sqlserver://localhost;databaseName=Investment
		spring.datasource.username=<<SET_YOUR_USERNAME>>
		spring.datasource.password=<<SET_YOUR_PASSWORD>>


# Install maven dependencies 

		mvn clean install

# Build and start the application

* Access swagger interface and test 
	
		http://localhost:8081/swagger-ui.html


![Alt text](./doc/swagger.png?raw=true "Swagger")

	