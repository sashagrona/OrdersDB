package net.bigmir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersDAO implements DAO {
    private Connection conn;
    private String table;

    public CustomersDAO(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }

    @Override
    public void initTable() {
        String query = "CREATE TABLE " + table + " (id SERIAL PRIMARY KEY, name VARCHAR(20) NOT NULL, surname VARCHAR(20) NOT NULL, age INT UNSIGNED)";
        new CommonDBOperation().init(table,query,conn);
    }

    @Override
    public void add(Object o) {
        try {
            Customer customer = (Customer) o;
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + table + " (name,surname,age) VALUES(?,?,?)");
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getSurname());
            ps.setInt(3,customer.getAge());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Object o) {
        Customer customer = (Customer) o;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + table + " WHERE id='" + customer.getId()+"'");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List getList() {
        List<Customer> customers = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setSurname(resultSet.getString(3));
                customer.setAge(resultSet.getInt(4));
                customers.add(customer);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
