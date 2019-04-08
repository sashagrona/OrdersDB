package net.bigmir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements DAO {
    private Connection conn;
    private String table;

    public ProductDao(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }

    @Override
    public void initTable() {
        String query = "CREATE TABLE " + table + " (id SERIAL PRIMARY KEY, name VARCHAR(30) NOT NULL, price DECIMAL(10,2) UNSIGNED)";
        new CommonDBOperation().init(table,query,conn);
    }

    @Override
    public void add(Object o) {
        try {
            Product product = (Product) o;
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + table + " (name,price) VALUES(?,?)");
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object o) {
        Product product = (Product) o;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + table + " WHERE id='" + product.getId() + "'");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List getList() {
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                products.add(product);

            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
