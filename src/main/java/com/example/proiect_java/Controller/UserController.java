package com.example.proiect_java.Controller;

import com.example.proiect_java.Model.User;
import com.example.proiect_java.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<Void> register(String name, String password, String email) {
        User user = new User(name,password,email);
        boolean isOkay = service.register(user);
        if (isOkay)
            return ResponseEntity.accepted().build();
        else
            return ResponseEntity.unprocessableEntity().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(String name, String password) {
        Boolean isOkay=service.login(name,password);
        if (isOkay)
            return ResponseEntity.accepted().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/changepassword")
    public ResponseEntity<Void> changePassword(String email, String oldPassword, String newPassword) {
        Boolean isOkay=service.changePassword(email,oldPassword,newPassword);
        if (isOkay)
            return ResponseEntity.accepted().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/addmoney")
    public ResponseEntity<Void> addMoney(int amount){
        if (service.getCurrentUser() != null){
            service.addMoney(amount);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/substractmoney")
    public ResponseEntity<Void> substractMoney(int amount){
        if (service.getCurrentUser() != null){
            if(service.substractMoney(amount))
                return ResponseEntity.ok().build();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/seemoney")
    public ResponseEntity<Long> seeMoney(){
        if (service.getCurrentUser() != null){
            return ResponseEntity.ok(service.seeMoney());
        }
        return ResponseEntity.badRequest().build();
    }
}
