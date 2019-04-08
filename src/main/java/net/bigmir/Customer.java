package net.bigmir;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String surname;
    private int age;
    private static int n=0;
    private List<Product> products = new LinkedList<>();

    public Customer(String name, String surname, int age) {
        n++;
        setId(n);
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addToList(Product product){
        products.add(product);
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
