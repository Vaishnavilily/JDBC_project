<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>

<%
    String URL = "jdbc:mysql://localhost:3306/Inventory";
    String USER = "root";
    String PASSWORD = "vaishu,lily@123";

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
%>

<html>
<head>
    <title>Inventory Management</title>
</head>
<body>
    <h1>Inventory Management System</h1>

    <%
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Product");

            while (rs.next()) {
                out.println("ID: " + rs.getInt("id") + "<br>");
                out.println("Name: " + rs.getString("name") + "<br>");
                out.println("Price: " + rs.getDouble("price") + "<br>");
                out.println("Quantity: " + rs.getInt("quantity") + "<br><br>");
            }

        } catch (Exception e) {
            out.println("An error occurred: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    %>

</body>
</html>
