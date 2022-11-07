package com.example.ecommerce.User;

import com.example.ecommerce.Products.Product;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "Users")
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDate dob;
//    @Column
//    private List<Product> productList;
    @Column
    private String password;
    @Column
    private String email;

    public AppUser() {
    }

    public AppUser(String name, LocalDate dob, String password, String email){
        this.dob = dob;
        this.name = name;
        this.password = password;
//        this.productList = new ArrayList<>();
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

//    public List<Product> getProductList() {
//        return productList;
//    }
//
//    public void setProductList(List<Product> productList) {
//        this.productList = productList;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
