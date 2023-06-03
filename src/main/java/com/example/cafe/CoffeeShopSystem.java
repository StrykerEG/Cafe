package com.example.cafe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoffeeShopSystem
{
    private final List<User> users;

    public CoffeeShopSystem() {
        super();
        this.users = new ArrayList<>();
        Cart cart = new Cart();
    }

    public void registerUser(String username, String password) {
        User user = new User(username, password,1000);
        users.add(user);

    }

    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.authenticate(username, password)) {
                return true;
            }
        }
        return false;
    }

    public boolean loginUserFromFile(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String storedUsername = userData[0];
                String storedPassword = userData[1];
                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    User user = new User(username,password,1000);
                    return true; // Login successful
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Login unsuccessful
    }

    public List<User> getUsers()
    {
        return users;
    }
}

