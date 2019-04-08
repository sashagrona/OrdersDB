package net.bigmir;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAO implements DAO {
    private Connection conn;
    private String table;
    private Map<Customer, List<Product>> orders;

    public OrderDAO(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }

    @Override
    public void initTable() {
        String query = "CREATE TABLE " + table + " (id SERIAL, customer_id BIGINT UNSIGNED, product_id BIGINT UNSIGNED NOT NULL, quantity INT NOT NULL,PRIMARY KEY(id),FOREIGN KEY(customer_id) REFERENCES Customers(id), FOREIGN KEY (product_id) REFERENCES Products(id) )";
        new CommonDBOperation().init(table, query, conn);
    }

    @Override
    public void add(Object o) {
        Order order = (Order) o;
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + table + " (customer_id, product_id,quantity) VALUES(?,?,?)");
            ps.setInt(1, order.getCustomer());
            ps.setInt(2, order.getProduct());
            ps.setInt(3, order.getQuantity());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object o) {
        Order order = (Order) o;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + table + " WHERE id='" + order.getId() + "'");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List getList() {
        List<Order> orders = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.setCustomer(resultSet.getInt(2));
                order.setProduct(resultSet.getInt(3));
                order.setQuantity(resultSet.getInt(4));
                orders.add(order);

            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Map<Customer, List<Product>> getCustomersProducts(Customer customer) {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT product_id FROM " + table + " WHERE customer_id='" + customer.getId()+"'");
            List<Product> products = new ArrayList<>();
            while (resultSet.next()){
                products.add(getProduct(resultSet.getInt(1)));

            }
            orders = new HashMap<>();
            orders.put(customer,products);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Customer getCustomer(int id) {
        DAO custom = new CustomersDAO(conn, "Customers");
        List<Customer> customers = custom.getList();
        for (Customer c : customers) {
            if (id == c.getId()) {
                return c;
            }
        }
        return null;
    }

    public Product getProduct(int id) {
        DAO prod = new ProductDao(conn, "Products");
        List<Product> products = prod.getList();
        for (Product p : products) {
            if (id == p.getId()) {
                return p;
            }
        }
        return null;
    }
}

