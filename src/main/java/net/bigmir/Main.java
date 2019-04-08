package net.bigmir;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection conn = cf.getConnection();

        DAO dao = new CustomersDAO(conn, "Customers");
        dao.initTable();
        Customer customer = new Customer("Sasha", "Grona", 20);
        dao.add(customer);

        Customer customer1 = new Customer("Oleg", "Valyn", 26);
        dao.add(customer1);
        DAO daoProducts = new ProductDao(conn, "Products");
        daoProducts.initTable();

        Product product = new Product("Iphone", 999);
        Product productOne = new Product("Samsung", 773);
        Product productTwo = new Product("Nokia", 123);

//        dao.delete(customer1);
        daoProducts.add(product);
        daoProducts.add(productOne);
        daoProducts.add(productTwo);
        DAO daoOrders = new OrderDAO(conn, "Orders");
        daoOrders.initTable();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Who you are?");
            int id = sc.nextInt();
            int quantity = 0;
            List<Customer> customers = dao.getList();
            for (Customer c : customers) {
                if (c.getId() == id) {
                    System.out.println("Hello " + c.getName() + "! What do you want to buy? Choose from the list:");
                    List<Product> products = daoProducts.getList();
                    System.out.println(products);
                    Scanner scanner = new Scanner(System.in);
                    int productID = scanner.nextInt();
                    for (Product p : products) {
                        if (p.getId() == productID) {
                            System.out.println("How many do you want?");
                            quantity = scanner.nextInt();
                            System.out.println("Confirm please, you're going to buy " + p.getName() + " ,yes?(y/n)");
                            Scanner s = new Scanner(System.in);
                            String confirm = s.nextLine();
                            if (confirm.equals("y")) {
                                daoOrders.add(new Order(c.getId(), p.getId(), quantity));
                                System.out.println("Order successed");
                            }
                        }
                    }
                    System.out.println("See your orders:");


                }
            }
            System.out.println(((OrderDAO) daoOrders).getCustomersProducts(customer));
            System.out.println(((OrderDAO) daoOrders).getCustomersProducts(customer1));
            System.out.println(daoOrders.getList());
//        System.out.println(dao.getList());
//        System.out.println(daoProducts.getList());
//        daoProducts.delete(productOne);
        }
    }
}
