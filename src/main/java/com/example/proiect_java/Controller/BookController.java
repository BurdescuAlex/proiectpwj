package com.example.proiect_java.Controller;

import com.example.proiect_java.Model.Book;
import com.example.proiect_java.Model.User;
import com.example.proiect_java.Service.BookService;
import com.example.proiect_java.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @PostMapping("/addbook")
    public ResponseEntity<Void> addBook(String title, String author, int price, boolean sellable){
        if(userService.getCurrentUser() != null) {
            if(bookService.addBook(title,author,price,userService.getCurrentUser(),sellable)){
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping("/changeprice")
    public ResponseEntity<Void> changePrice(long id,int price){
        if(bookService.changePrice(id,price,userService.getCurrentUser())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/sellstate")
    public ResponseEntity<Void> sellState(long id, boolean sellable){
        if(bookService.sellState(id,sellable,userService.getCurrentUser())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/buybook")
    public ResponseEntity<Void> buyBook(long id)
    {
        if(bookService.buyBook(id,userService.getCurrentUser())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/seebooks")
    public ResponseEntity<List<Book>> seeBooks()
    {
        if (bookService.seeBooks(userService.getCurrentUser())!=null)
            return ResponseEntity.ok(bookService.seeBooks(userService.getCurrentUser()));
        return ResponseEntity.noContent().build();
    }
}
