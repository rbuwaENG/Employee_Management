package dbapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class dbconnect {
    private static final String URL = "jdbc:mysql://localhost:3306/employeedb?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private Connection conn;

    public void connect() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEmployee(String fname, String minit, String lname, int ssn,
                               String bdate, String address, String sex, double salary, Integer superSsn, int dno) throws SQLException {
        String insertQuery = "INSERT INTO employee (fname, minit, lname, ssn, bdate, address, sex, salary, super_ssn, dno) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
        insertStmt.setString(1, fname);
        insertStmt.setString(2, minit);
        insertStmt.setString(3, lname);
        insertStmt.setInt(4, ssn);
        insertStmt.setString(5, bdate);
        insertStmt.setString(6, address);
        insertStmt.setString(7, sex);
        insertStmt.setDouble(8, salary);
        insertStmt.setObject(9, superSsn);
        insertStmt.setInt(10, dno);

        int rowsInserted = insertStmt.executeUpdate();
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(null, "Employee inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Employee inserted successfully.");
        }
    }

    public void updateEmployee(int empId, String fname, String minit, String lname, int ssn,
                               String bdate, String address, String gender, double salary, Integer superSsn, int dno) throws SQLException {
        String updateQuery = "UPDATE employee SET fname = ?, minit = ?, lname = ?, ssn = ?, bdate = ?, address = ?, sex = ?, salary = ?, super_ssn = ?, dno = ? WHERE ssn = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setString(1, fname);
        updateStmt.setString(2, minit);
        updateStmt.setString(3, lname);
        updateStmt.setInt(4, ssn);
        updateStmt.setString(5, bdate);
        updateStmt.setString(6, address);
        updateStmt.setString(7, gender);
        updateStmt.setDouble(8, salary);
        updateStmt.setObject(9, superSsn);
        updateStmt.setInt(10, dno);
        updateStmt.setInt(11, empId);

        int rowsUpdated = updateStmt.executeUpdate();
        if (rowsUpdated > 0) {
              JOptionPane.showMessageDialog(null, "Employee updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Employee updated successfully.");
        }
    }

    public void deleteEmployee(int empId) throws SQLException {
        String deleteQuery = "DELETE FROM employee WHERE ssn = ?";
        PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
        deleteStmt.setInt(1, empId);

        int rowsDeleted = deleteStmt.executeUpdate();
        if (rowsDeleted > 0) {
              JOptionPane.showMessageDialog(null, "Employee deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Employee deleted successfully.");
        }
    }
}