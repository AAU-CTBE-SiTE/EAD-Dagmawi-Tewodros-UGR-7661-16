package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/StudentsDB";
        String username = "root";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM students")) {

            System.out.println("Established Connection");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " "
                        + rs.getString("firstname") + " "
                        + rs.getString("lastname") + " "
                        + rs.getInt("grade"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
