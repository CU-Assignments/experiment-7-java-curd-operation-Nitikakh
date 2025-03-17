CREATE TABLE Employee (
    EmpID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Salary DECIMAL(10, 2) NOT NULL
);

INSERT INTO Employee (Name, Salary) VALUES 
('Alice', 50000),
('Bob', 60000),
('Charlie', 55000);


package org.example;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Employees";
        String user = "root";
        String password = "Sonu@932014";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Employee")) {

            while (rs.next()) {
                System.out.println("EmpID: " + rs.getInt("EmpID") +
                        ", Name: " + rs.getString("Name") +
                        ", Salary: " + rs.getDouble("Salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

