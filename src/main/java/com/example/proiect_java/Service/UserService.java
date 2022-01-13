package com.example.proiect_java.Service;


import com.example.proiect_java.Model.User;
import com.example.proiect_java.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Boolean register(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return false;
        }
        try {
            userRepository.save(user);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public Boolean login(String name, String password)
    {
        Optional<User> existingUser = userRepository.findByName(name);
        if(existingUser.isPresent()) {
            if(existingUser.get().getPassword().equals(password)) {
                this.currentUser = existingUser.get();
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public Boolean changePassword(String email, String oldPassword, String newPassword){
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()){
            if(existingUser.get().getPassword().equals(oldPassword))
            {
                existingUser.get().setPassword(newPassword);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public Boolean addMoney(int amount)
    {
        try {
            this.currentUser.addMoney(amount);
            return true;
        }
        catch (RuntimeException e){
            return false;
        }
    }

    public Boolean substractMoney(int amount)
    {
        if(amount > currentUser.getMoney())
            return false;
        try {
            this.currentUser.substractMoney(amount);
            return true;
        }
        catch (RuntimeException e){
            return false;
        }
    }

    public long seeMoney()
    {
        return this.currentUser.getMoney();
    }
}
