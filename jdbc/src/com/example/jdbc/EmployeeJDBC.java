package com.example.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeJDBC {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employees";
    private static final String JDBC_USER = "user";
    private static final String JDBC_PASSWORD = "deep6174";
    public static void main(String[] args) {
        // Create an instance of EmployeeJDBC
        EmployeeJDBC employeeJDBC = new EmployeeJDBC();
    
        // Test adding an employee
        Employee newEmployee = new Employee(1, "John Doe", "IT");
        boolean added = employeeJDBC.addEmployee(newEmployee);
        System.out.println("Employee added: " + added);
    
        // Test getting an employee by ID
        int employeeId = 1;
        System.out.println("\nFetching employee with ID: " + employeeId);
        employeeJDBC.getEmployeeById(employeeId);
    
        // Test updating an employee
        String newName = "Jane Smith";
        String newDepartment = "Finance";
        boolean updated = employeeJDBC.updateEmployee(employeeId, newName, newDepartment);
        System.out.println("\nEmployee updated: " + updated);
    
        // Test deleting an employee
        System.out.println("\nDeleting employee with ID: " + employeeId);
        boolean deleted = employeeJDBC.deleteEmployee(employeeId);
        System.out.println("Employee deleted: " + deleted);
    
        // Test fetching all employees
        System.out.println("\nFetching all employees:");
        employeeJDBC.getAllEmployees();
    }
    

    public Connection makeConnection() throws SQLException{
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void closeResources(Connection connection, Statement statement, PreparedStatement preparedStatement, ResultSet resultSet){
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            if(preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean addEmployee(Employee employee){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO empTable (id, name, department) VALUES (?,?,?)";

        try{
            connection = makeConnection();
            System.out.println(connection);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getDepartment());
            int insert = preparedStatement.executeUpdate();
            closeResources(connection, null, preparedStatement, null);
            if(insert > 0){
                System.out.println("Employee details added!");
                return true;
            }
        }catch(SQLException e){
            System.out.println("Error adding employee: "+ e.getMessage());
        }
        return false;
    }

    public void getEmployeeById(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM empTable WHERE id = ?";

        try{
            connection = makeConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No employee found with ID: " + id);
            }else{
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String department = resultSet.getString("department");
                    System.out.println("ID: " + id + ", Name: " + name + ", Department: " + department);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        closeResources(connection, null, preparedStatement, resultSet);
    }

    public void getAllEmployees(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM empTable";

        try{
            connection = makeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                System.out.println("ID: " + id + ", Name: " + name + ", Department: " + department);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        closeResources(connection, statement, null, resultSet);
    }

    public boolean updateEmployee(int id, String name, String department){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE empTable SET name = ?, department = ? WHERE id = ?";

        try{
            connection = makeConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, department);
            preparedStatement.setInt(3, id);
            int insert = preparedStatement.executeUpdate();
            closeResources(connection, null, preparedStatement, null);
            if(insert > 0){
                System.out.println("Employee details updated successfully!");
                return true;
            }else{
                System.out.println(String.format("Employee with %d not found", id));
                return false;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deleteEmployee(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM empTable WHERE id = ?";

        try{
            connection = makeConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int insert = preparedStatement.executeUpdate();
            closeResources(connection, null, preparedStatement, null);
            if(insert > 0){
                System.out.println("Employee details deleted!");
                return true;
            }else{
                System.out.println(String.format("Employee with %d not found", id));
                return false;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
