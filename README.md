# Inventory Tracking System
It is a Simple and functional Inventory Tracking System that allows Users to manage Products, Track Inventory Levels, and Perform Basic CRUD(Create, Read, Update, Delete) Operations On a MySql Database.
This Project Uses JDBC Concepts to connect the Users to the Database.

## Pre-requirements

Before running the project, ensure you have the following set up:

1. **MySQL Database Server:** Make sure you have MySQL installed and running on your system. You can download it from the [official MySQL website](https://dev.mysql.com/downloads/mysql/).

2. **JDBC Driver:** You need the MySQL Connector/J driver to connect your Java application to the MySQL database. You can download it from the [official MySQL Connector/J page](https://dev.mysql.com/downloads/connector/j/).

3. **Database Setup:**
    - Open your MySQL workspace (such as MySQL Workbench) and execute the following SQL queries to set up the database and table required for the project.

    ```sql
    -- Create the Inventory database
    CREATE DATABASE Inventory;

    -- Use the Inventory database
    USE Inventory;

    -- Create the product table
    CREATE TABLE product (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        price DOUBLE NOT NULL,
        quantity INT NOT NULL
    );
    ```

4. **Project Configuration:**
    - Update your JDBC connection URL, username, and password in the project configuration to match your MySQL server settings.

### Example Configuration

In your Java project, ensure your JDBC connection string and credentials are correctly configured. For example:

```java
String url = "jdbc:mysql://localhost:3306/Inventory";
String username = "your_mysql_username";
String password = "your_mysql_password";

Connection connection = DriverManager.getConnection(url, username, password);
```
## Connect Main Java file with JSP file

1. Place your jspJava.java file in the webapps/your-web-app-name directory within your Tomcat installation directory. 
2. Access the JSP file through a web browser using the appropriate URL, typically http://localhost:8080/your-web-app-name/jspJava.java.

## Run the Project

1. Ensure your MySQL server is running.
2. Start the Apache Tomcat server.
3. Access your project through a web browser by entering the appropriate URL, typically http://localhost:8080/your-web-app-name.
   
## Progress:

1. I have Connected the JDBC and loaded necessary Drivers into my Project
2. The Users can select which CRUD Operation to Perform on the Database through Command-Line Interface

Feel free to reach out if you have any questions or need further assistance with running your Inventory Tracking System project with Apache Tomcat integration!

```python
a=10
print(a)
```
