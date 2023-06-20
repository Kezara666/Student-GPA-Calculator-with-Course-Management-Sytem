package com.example.demo2;

import java.sql.*;

public class DBOperations {
    public String Authention_Redirect(String username,String password) throws SQLException {
        String role="not User";
        Connection connection=DBUtils.getConnection();
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);  // Set the value of the first parameter
        statement.setString(2, password);  // Set the value of the second parameter

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Retrieve data from the result set
            role = resultSet.getString("role");

            // Do something with the retrieved data


        }


// Close the resources
        resultSet.close();
        statement.close();
        return role;

    }

    public void insertDataIntoGpa(int idgpa, String c_name, int credit, String grade, double gpa) {
        String sql = "INSERT INTO gpa (idgpa, c_name, credit, grade, gpa) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idgpa);          // Set the value for idgpa parameter
            statement.setString(2, c_name);      // Set the value for c_name parameter
            statement.setInt(3, credit);         // Set the value for credit parameter
            statement.setString(4, grade);       // Set the value for grade parameter
            statement.setDouble(5, gpa);         // Set the value for gpa parameter

            // Execute the statement
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
