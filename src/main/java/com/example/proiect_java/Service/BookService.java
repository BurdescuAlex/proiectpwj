package com.example.proiect_java.Service;

import com.example.proiect_java.Model.Book;
import com.example.proiect_java.Model.Transaction;
import com.example.proiect_java.Model.User;
import com.example.proiect_java.Repository.BookRepository;
import com.example.proiect_java.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;
    public TransactionRepository transactionRepository;

    public Boolean addBook(String title, String author, int price, User user, boolean sellable) {
        Book newBook = new Book(title, author, price, user, sellable);
        try {
            bookRepository.save(newBook);
            user.addToLibrary(newBook);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public Boolean changePrice(long id, int price, User user){
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            if(existingBook.get().getUser().equals(user)){
                existingBook.get().setPrice(price);
                return true;
            }
            return false;
        }
        return false;
    }

    public Boolean sellState(long id, boolean sellable, User user){
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            if(existingBook.get().getUser().equals(user)){
                existingBook.get().setSellable(sellable);
                return true;
            }
            return false;
        }
        return false;
    }

    public Boolean buyBook(long id, User buyer){
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            if(!existingBook.get().getUser().equals(buyer) && existingBook.get().isSellable()){
                if(buyer.getMoney() >= existingBook.get().getPrice()) {
                    buyer.substractMoney(existingBook.get().getPrice());
                    buyer.addToLibrary(existingBook.get());
                    User seller = existingBook.get().getUser();
                    seller.removeFromLibrary(existingBook.get());
                    existingBook.get().setUser(buyer);
                    transactionRepository.save(new Transaction(buyer,seller,existingBook.get()));
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public Set<Book> seeBooks(User user)
    {
        return user.getLibrary();
    }

}

