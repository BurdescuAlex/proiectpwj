package com.example.proiect_java.Model;
import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="buyer_id", referencedColumnName = "id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name="seller_id", referencedColumnName = "id")
    private User seller;

    @ManyToOne
    @JoinColumn(name="book_id", referencedColumnName = "id")
    private Book book;

    @Column
    private int price;

    public Transaction(){}

    public Transaction(User buyer, User seller, Book book)
    {
        this.buyer=buyer;
        this.seller=seller;
        this.price=book.getPrice();
        this.book=book;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
