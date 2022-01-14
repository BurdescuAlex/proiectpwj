package com.example.proiect_java.Service;

import com.example.proiect_java.Model.Book;
import com.example.proiect_java.Model.Transaction;
import com.example.proiect_java.Model.User;
import com.example.proiect_java.Repository.BookRepository;
import com.example.proiect_java.Repository.TransactionRepository;
import com.example.proiect_java.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public TransactionRepository transactionRepository;
    @Autowired
    public UserRepository userRepository;

    public Boolean addBook(String title, String author, int price, User user, boolean sellable) {
        Book newBook = new Book(title, author, price, user, sellable);
        try {
            bookRepository.save(newBook);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public Boolean changePrice(long id, int price, User user){
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            if(existingBook.get().getUser().getId() == user.getId()){
                existingBook.get().setPrice(price);
                bookRepository.save(existingBook.get());
                return true;
            }
            return false;
        }
        return false;
    }

    public Boolean sellState(long id, boolean sellable, User user){
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            if(existingBook.get().getUser().getId() == user.getId()){
                existingBook.get().setSellable(sellable);
                bookRepository.save(existingBook.get());
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
                    User seller = existingBook.get().getUser();
                    seller.addMoney(existingBook.get().getPrice());
                    existingBook.get().setUser(buyer);
                    userRepository.save(buyer);
                    userRepository.save(seller);
                    bookRepository.save(existingBook.get());
                    transactionRepository.save(new Transaction(buyer,seller,existingBook.get()));
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public List<Book> seeBooks(User user)
    {
        List<Book> library = new ArrayList<Book>();
        for(Book book : bookRepository.findAll())
        {
            if(book.getUser().getId() == user.getId())
            {
                library.add(book);
            }
        }
        return library;
    }

}

