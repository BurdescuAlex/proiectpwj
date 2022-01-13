package com.example.proiect_java.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private long money;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<Book> library;

    public User(){}

    public User(String name, String password, String email)
    {
        this.name = name;
        this.password = password;
        this.email = email;
        this.money=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Set<Book> getLibrary() {
        return library;
    }

    public void addToLibrary(Book book) {
        this.library.add(book);
    }

    public void removeFromLibrary(Book book) {this.library.remove(book);}

    public long getMoney() {
        return money;
    }

    public void addMoney(long money) {
        this.money += money;
    }

    public void substractMoney(long money) {
        this.money -= money;
    }
}
